package AST;

import AST.Nodes.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeGenerator implements Visitor {
    private StringBuilder codeBuilder;
    private SymbolTableFilling symbolTable;
    private int stackAddress = 0xff;
    private int computingCount = 0;
    private int binOperatorCount = 0;
    private int labelCount = 0;
    // Gør det muligt at bruge float i vores kode, og kan adskille dem fra hinanden for 1/8.
    // Eksempel: 0,124 = 0, 0,125 = 1, 0,25 = 2
    // Maks værdi er 31,875
    private static final int FIXED_POINT = 3;
    private static final int ONE = 1 << FIXED_POINT;
    private static int toFixedPoint(float value) {
        return (int) (value * ONE);
    }

    private void incrementStackAddress() {
        stackAddress++;
    }
    private void decrementStackAddress() {
        stackAddress--;
    }

    private void pushAccumulator() {
        codeBuilder.append("PHA\n");
        decrementStackAddress();
    }

    private void pullAccumulator() {
        codeBuilder.append("PLA\n");
        incrementStackAddress();
    }
    private void loadXRegisterWithConst(int value) {
        codeBuilder.append("LDX #" + value + "\n");
    }

    private void storeXRegisterInVariable(String id) {
        Symbol symbol = symbolTable.lookup(id);
        codeBuilder.append("STX $01" + Integer.toHexString(symbol.getMemoryAddress()) + "\n");
    }

    public CodeGenerator(SymbolTableFilling symbolTable) {
        codeBuilder = new StringBuilder();
        this.symbolTable = symbolTable;
    }

    public void generateCode() {
        // Write the generated code to a file
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            writer.print(codeBuilder.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Could not write output file: " + e.getMessage());
        }
    }

    @Override
    public void visit(Assigning node) {
        if (node.getDeclaration() instanceof Id) {
            node.getExpression().accept(this);
            if (node.getExpression() instanceof Computing) {
                codeBuilder.append("TAX\n");
            }
            storeXRegisterInVariable(((Id) node.getDeclaration()).getName());
        } else {
            node.getDeclaration().accept(this);
            node.getExpression().accept(this);
            if (!(node.getExpression() instanceof Computing)) {
                codeBuilder.append("TXA\n");
            }
            pushAccumulator();
        }
        computingCount = 0;
    }

    @Override
    public void visit(BinOperator node) {
        evaluateBinOperator(node);
    }

    @Override
    public void visit(Block node) {
        for (Node n : node.getChildren()) {
            codeBuilder.append("ifthen" + labelCount + ":\n");
            n.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {
        if (node.getValue()) {
            loadXRegisterWithConst(1);
        } else {
            loadXRegisterWithConst(0);
        }
    }

    @Override
    public void visit(BoolDcl node) {
        symbolTable.lookup(node.getId()).setMemoryAddress(stackAddress);
    }

    @Override
    public void visit(Computing node) {
        codeBuilder.append("CLC\n");
        computingtest(node);
    }

    @Override
    public void visit(FloatDcl node) {
        symbolTable.lookup(node.getId()).setMemoryAddress(stackAddress);
    }

    @Override
    public void visit(FloatNum node) {
        loadXRegisterWithConst(toFixedPoint(node.getValue()));
    }

    @Override
    public void visit(Id node) {
        // Man skriver $01 foran adressen fordi den metode man kalder efter er 8-bit og derfor kun 2 decimaler.
        // Når vi skriver $01 foran ender vi i stacken.
        codeBuilder.append("LDX $01" + Integer.toHexString(symbolTable.lookup(node.getName()).getMemoryAddress()) + "\n");
    }

    @Override
    public void visit(If node) {
        node.getCondition().accept(this);
        binOperatorCount = 0;
        if (node.getCondition() instanceof Bool) {
            codeBuilder.append("TXA\n");
        } else if (node.getCondition() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append("CMP #1\n");
        codeBuilder.append("BNE end" + labelCount + "\n");
        codeBuilder.append("ifthen" + labelCount + ":\n");
        node.getThenBlock().accept(this);
        codeBuilder.append("end" + labelCount + ":\n");
        labelCount++;
    }

    @Override
    public void visit(IfElse node) {
        node.getCondition().accept(this);
        binOperatorCount = 0;
        if (node.getCondition() instanceof Bool) {
            codeBuilder.append("TXA\n");
        } else if (node.getCondition() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append("CMP #1\n");
        codeBuilder.append("BNE else" + labelCount + "\n");
        codeBuilder.append("ifthen" + labelCount + ":\n");
        node.getThenBlock().accept(this);
        codeBuilder.append("JMP end" + labelCount + "\n");
        codeBuilder.append("else" + labelCount + ":\n");
        node.getElseBlock().accept(this);
        codeBuilder.append("end" + labelCount + ":\n");
        labelCount++;
    }

    @Override
    public void visit(IntDcl node) {
        Symbol symbol = symbolTable.lookup(node.getId());
        symbol.setMemoryAddress(stackAddress);
    }

    @Override
    public void visit(IntNum node) {
        loadXRegisterWithConst(node.getValue());
    }

    @Override
    public void visit(Not node) {
        node.getExpression().accept(this);
        codeBuilder.append("TXA\n");
        codeBuilder.append("EOR #1\n");
    }

    @Override
    public void visit(Print node) {

    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
        System.out.println(stackAddress);
        codeBuilder.append("BRK\n");
    }

    public void addTwoNumbers(Computing node) {
        codeBuilder.append("STX $0100\n");
        if (node.getOperator().equals("+")) {
            codeBuilder.append("ADC $0100\n");
        } else {
            codeBuilder.append("SBC $0100\n");
            codeBuilder.append("CLC\n");
            codeBuilder.append("ADC #1\n");
        }
    }

    public void computingtest(Computing node) {
        node.getLeftOperand().accept(this);
        if (computingCount == 0) {
            codeBuilder.append("TXA\n");
            computingCount++;
        }

        if (node.getRightOperand() instanceof Computing) {
            ((Computing) node.getRightOperand()).getLeftOperand().accept(this);
            addTwoNumbers(node);
            ((Computing) node.getRightOperand()).getRightOperand().accept(this);
            addTwoNumbers((Computing) node.getRightOperand());
        } else {
            node.getRightOperand().accept(this);
            addTwoNumbers(node);
        }
    }

    public void evaluateBinOperator(BinOperator node){
        node.getLeftOperand().accept(this);
        if (node.getLeftOperand() instanceof Id ||
            node.getLeftOperand() instanceof IntNum ||
            node.getLeftOperand() instanceof FloatNum ||
            node.getLeftOperand() instanceof Bool)
        {
            codeBuilder.append("TXA\n");
            pushAccumulator();
        } else if (node.getLeftOperand() instanceof Computing) {
            pushAccumulator();
        }
        node.getRightOperand().accept(this);
        if (node.getRightOperand() instanceof Computing) {
            codeBuilder.append("TAX\n");
            pullAccumulator();
        } else if ((node.getOperator().equals("||") || node.getOperator().equals("&&"))
                && binOperatorCount > 0
                && !(node.getRightOperand() instanceof Bool)
                && !(node.getRightOperand() instanceof Not))
        {
            pullAccumulator();
            codeBuilder.append("TAX\n");
            pullAccumulator();
        } else {
            pullAccumulator();
        }
        compareTwoBooleans(node);
        computingCount = 0;
    }

    public void compareTwoBooleans(BinOperator node) {
        codeBuilder.append("STX $0100\n");
        codeBuilder.append("CLC\n");
        switch (node.getOperator()) {
            case "==" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BNE false" + binOperatorCount + "\n");
            }
            case "!=" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ false" + binOperatorCount + "\n");
            }
            case "||" -> {
                codeBuilder.append("ORA $0100\n");
                codeBuilder.append("BEQ false" + binOperatorCount + "\n");
            }
            case "&&" -> {
                codeBuilder.append("AND $0100\n");
                codeBuilder.append("BEQ false" + binOperatorCount + "\n");
            }
            case "<" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ false" + binOperatorCount + "\n");
                codeBuilder.append("BCS false" + binOperatorCount + "\n");
            }
            case "<=" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BCS false" + binOperatorCount + "\n");
            }
            case ">" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ false" + binOperatorCount + "\n");
                codeBuilder.append("BCC false" + binOperatorCount + "\n");
            }
            case ">=" -> {
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BCC false" + binOperatorCount + "\n");
            }
        }
        codeBuilder.append("LDA #1\n");
        codeBuilder.append("JMP store" + binOperatorCount + "\n");
        codeBuilder.append("false" + binOperatorCount + ":\n");
        codeBuilder.append("  LDA #0\n");
        codeBuilder.append("store" + binOperatorCount + ":\n");
        binOperatorCount++;
        codeBuilder.append("  "); // gør at der kommer indent.
        pushAccumulator();
    }
}
