package AST;

import AST.Types.Type;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeGenerator implements Visitor {
    private StringBuilder codeBuilder;
    private SymbolTableFilling symbolTable;
    private int stackAddress = 0xff;
    private int computingCount = 0;
    private int binOperatorCount = 0;
    private int labelCount = 0;
    private int ifCount = 0;
    private String nextOperator = "";

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

    private void loadXRegisterWithVariable(String id) {
        Symbol symbol = symbolTable.lookup(id);
        codeBuilder.append("LDX " + symbol.getMemoryAddress() + "\n");
    }

    private void storeXRegisterInVariable(String id) {
        Symbol symbol = symbolTable.lookup(id);
        codeBuilder.append("STX " + symbol.getMemoryAddress() + "\n");
    }

    public String stackAddressToString(){
        return Integer.toHexString(stackAddress).toUpperCase();
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
        node.child2.accept(this);
        node.child1.accept(this);
        if (!(node.child2 instanceof Computing)) {
            codeBuilder.append("TXA\n");
        }
        codeBuilder.append("PHA\n");
        computingCount = 0;
    }

    @Override
    public Type visit(BinOperator node) {
        /*if (!(node.getLeftOperand() instanceof BinOperator)) {
            codeBuilder.append("binOperation" + binOperatorCount + ":\n");
            binOperatorCount++;
        } else {
            nextOperator = node.getOperator();
        }
        node.getLeftOperand().accept(this);
        // Uanset hvad smider vi left operanden på stacken.
        if (node.getLeftOperand() instanceof Computing) {
            pushAccumulator();
        } else if (node.getLeftOperand() instanceof Id ||
                node.getLeftOperand() instanceof IntNum ||
                node.getLeftOperand() instanceof FloatNum ||
                node.getLeftOperand() instanceof Bool) {
            codeBuilder.append("TXA\n");
            pushAccumulator();
        }
        nextOperator = node.getOperator();
        node.getRightOperand().accept(this);
        if (node.getRightOperand() instanceof Computing) {
            codeBuilder.append("TAX\n");
        }
        pullAccumulator();

        switch (node.getOperator()) {
            case "||":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("ORA $0100\n");
                // BEQ = false
                // BNE = true
                break;
            case "&&":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("AND $0100\n");
                // BEQ = true
                // BNE = false
                break;
            case "==":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                // BEQ = true
                // BNE = false
                break;
            case "!=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                // BNE = true
                // BEQ = false
                break;
            case "<", "<=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                // BCC = true
                // BCS = false
                break;
            case ">", ">=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                // BCS = true
                // BCC = false
                break;
        }*/
        evaluateBinoperator(node);
        return null;
    }

    @Override
    public void visit(Block node) {
        for (Node n : node.children) {
            codeBuilder.append("ifthen" + labelCount + ":\n");
            n.accept(this);
            labelCount++;
        }
    }

    @Override
    public void visit(Bool node) {
        if (node.value.equals("true")) {
            loadXRegisterWithConst(1);
        } else {
            loadXRegisterWithConst(0);
        }
    }

    @Override
    public void visit(BoolDcl node) {
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        decrementStackAddress();
    }

    @Override
    public Type visit(Computing node) {
        computingForIntNode(node);
        return null;
    }

    @Override
    public void visit(FloatDcl node) {
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        decrementStackAddress();
        decrementStackAddress();
    }

    @Override
    public void visit(FloatNum node) {
        // .split("\\.") splits the string at the dot into an array with two entries.
        String[] parts = node.value.split("\\.");
        loadXRegisterWithConst(Integer.parseInt(parts[0]));
        codeBuilder.append("TXA\n");
        pushAccumulator();
        loadXRegisterWithConst(Integer.parseInt(parts[1]));
    }

    @Override
    public Type visit(Id node) {
        // Man skriver $01 foran adressen fordi den metode man kalder efter er 8-bit og derfor kun 2 decimaler.
        // Når vi skriver $01 foran ender vi i stacken.
        codeBuilder.append("LDX $01" + Integer.toHexString(symbolTable.lookup(node.id).getMemoryAddress()) + "\n");
        return null;
    }

    @Override
    public void visit(If node) {
        node.getCondition().accept(this);
        node.getThenBlock().accept(this);
        codeBuilder.append("end" + (labelCount - 1) + ":\n");
        decrementStackAddress();
    }

    @Override
    public void visit(IfElse node) {
    }

    @Override
    public void visit(IntDcl node) {
        Symbol symbol = symbolTable.lookup(node.id);
        symbol.setMemoryAddress(stackAddress);
        decrementStackAddress();
    }

    @Override
    public void visit(IntNum node) {
        loadXRegisterWithConst(Integer.parseInt(node.value));
    }

    @Override
    public Type visit(Not node) {
        return null;
    }

    @Override
    public void visit(Print node) {

    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.println(stackAddress);
        codeBuilder.append("BRK\n");
        codeBuilder.append("push_true:\n");
        codeBuilder.append("LDA #$01\n");
        codeBuilder.append("PHA\n");
    }
    public void computingForIntNode(Computing node) {
        if (node.getLeftOperand() instanceof Computing) {
            node.getLeftOperand().accept(this);
            node.getRightOperand().accept(this);
            if (computingCount == 0) {
                codeBuilder.append("TXA\n");
            } else {
                if (!(node.getRightOperand() instanceof Computing)) {
                    codeBuilder.append("STX $0100\n");
                    codeBuilder.append("CLC\n");
                    if (node.getOperator().equals("+")) {
                        codeBuilder.append("ADC $0100\n");
                    } else {
                        codeBuilder.append("SBC $0100\n");
                        codeBuilder.append("EOR #$FF\n");
                    }
                }
            }
            computingCount++;
        } else {
            node.getRightOperand().accept(this);
            if (computingCount == 0) {
                codeBuilder.append("TXA\n");
            } else {
                if (!(node.getRightOperand() instanceof Computing)) {
                    codeBuilder.append("STX $0100\n");
                    codeBuilder.append("CLC\n");
                    if (node.getOperator().equals("+")) {
                        codeBuilder.append("ADC $0100\n");
                    } else {
                        codeBuilder.append("SBC $0100\n");
                        codeBuilder.append("EOR #$FF\n");
                    }
                }
            }
            computingCount++;
            node.getLeftOperand().accept(this);
            codeBuilder.append("STX $0100\n");
            codeBuilder.append("CLC\n");
            if (node.getOperator().equals("+")) {
                codeBuilder.append("ADC $0100\n");
            } else {
                codeBuilder.append("SBC $0100\n");
                codeBuilder.append("EOR #$FF\n");
            }
        }
    }

    public void evaluateBinoperator(BinOperator node) {
        node.getLeftOperand().accept(this);
        if (node.getLeftOperand() instanceof Id ||
            node.getLeftOperand() instanceof IntNum ||
            node.getLeftOperand() instanceof FloatNum ||
            node.getLeftOperand() instanceof Bool)
        {
            codeBuilder.append("TXA\n");
        }
        pushAccumulator();
        node.getRightOperand().accept(this);
        if (node.getLeftOperand() instanceof Computing) {
            codeBuilder.append("TAX\n");
        }
        pullAccumulator();
        codeBuilder.append("CLC\n");
        // || and && mangler at blive fikset, der skal den enkelte operand evalueres
        // i stedet for det pjat hernede.
        switch (node.getOperator()) {
            case "||":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ ifthen" + labelCount + "\n");
                break;
            case "&&":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BNE end" + labelCount + "\n");
                break;
            case "==":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ ifthen" + labelCount + "\n");
                break;
            case "!=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BNE ifthen" + labelCount + "\n");
                break;
            case "<":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ end" + labelCount + "\n");
                codeBuilder.append("BCS end" + labelCount + "\n");
                // BCC = true
                // BCS = false
                break;
            case "<=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BCS end" + labelCount + "\n");
                // BCS = true
                // BCC = false
                break;
            case ">":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ end" + labelCount + "\n");
                codeBuilder.append("BCC end" + labelCount + "\n");
                // BCS = true
                // BCC = false
                break;
            case ">=":
                codeBuilder.append("STX $0100\n");
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BCC end" + labelCount + "\n");
                // BCS = true
                // BCC = false
                break;
        }
    }

    public void binOperatortest(BinOperator node) {
        node.getLeftOperand().accept(this);
        if (node.getLeftOperand() instanceof Id ||
            node.getLeftOperand() instanceof IntNum ||
            node.getLeftOperand() instanceof FloatNum ||
            node.getLeftOperand() instanceof Bool)
        {
            codeBuilder.append("TXA");
        }
        pushAccumulator();

        node.getRightOperand().accept(this);
        if (node.getRightOperand() instanceof Computing) {
            codeBuilder.append("TAX");
        }

        pullAccumulator();

        codeBuilder.append("STX $0100\n");
        codeBuilder.append("CLC\n");

        switch (node.getOperator()) {
            case "||":
                codeBuilder.append("CMP $0100\n");
                codeBuilder.append("BEQ ifthen" + labelCount + "\n");
                break;
        }
    }
}
