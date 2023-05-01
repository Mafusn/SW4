package AST.CodeGeneration;

import AST.Nodes.*;
import AST.SymbolTableFilling.Symbol;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Visitor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CodeGenerator implements Visitor {
    private int scopeLevel = 0;
    private StringBuilder codeBuilder;
    private final ArrayList<SymbolTableFilling> symbolTables;
    private int stackAddress = 0xff;
    private int arithmeticOpCount = 0;
    private ArrayList operators = new ArrayList<Integer>();
    private int binOperatorCount = 0;
    private int labelCount = 0;
    private int whileLoopCount = 0;
    private int blockCount = 0;
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
        codeBuilder.append(InstructionSet.PLA.getInstruction() + "\n");
        incrementStackAddress();
    }

    private int getScopeLevel() {
        // Hvis blockCount er 0, så har vi kun adgang til variablerne i global scope.
        if (blockCount == 0) {
            return 0;
        }
        return scopeLevel;
    }
    private void loadXRegisterWithConst(int value) {
        codeBuilder.append(InstructionSet.LDX.getInstruction() + " #" + value + "\n");
    }

    private void storeXRegisterInVariable(String id) {
        System.out.println("scopeLevel: " + getScopeLevel());
        Symbol symbol = symbolTables.get(getScopeLevel()).lookup(id);
        codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + Integer.toHexString(symbol.getMemoryAddress()) + "\n");
    }

    public CodeGenerator(ArrayList<SymbolTableFilling> symbolTables) {
        codeBuilder = new StringBuilder();
        this.symbolTables = symbolTables;
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
    public void visit(AssignmentOp node) {
        if (node.getDeclaration() instanceof Id) {
            node.getExpression().accept(this);

            if (node.getExpression() instanceof ArithmeticOp) {
                clearTheBottomOfStackForArithmeticOp();
                codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
            }
            storeXRegisterInVariable(((Id) node.getDeclaration()).getName());
        } else {
            node.getDeclaration().accept(this);
            node.getExpression().accept(this);

            if (node.getExpression() instanceof ArithmeticOp) {
                clearTheBottomOfStackForArithmeticOp();
            } else {
                codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
            }

            pushAccumulator();
        }
    }

    @Override
    public void visit(ComparisonOp node) {
        evaluateBinOperator(node);
    }

    @Override
    public void visit(Block node) {
        blockCount++;
        int stackAddressPlaceHolder = stackAddress;
        scopeLevel++;
        for (Node n : node.getChildren()) {
            n.accept(this);
        }
        System.out.println("StackAddressPlaceHolder: " + stackAddressPlaceHolder + " " + "stackAddress: " + stackAddress);
        for (int i = stackAddressPlaceHolder - stackAddress; i > 0; i--) {
            codeBuilder.append(InstructionSet.PLA.getInstruction() + "\n");
        }
        stackAddress = stackAddressPlaceHolder;
        blockCount--;
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
        symbolTables.get(getScopeLevel()).lookup(node.getId()).setMemoryAddress(stackAddress);
    }

    @Override

    public void visit(ArithmeticOp node) {
        evaluateArithmeticOp(node);
    }

    @Override
    public void visit(FloatDcl node) {
        symbolTables.get(getScopeLevel()).lookup(node.getId()).setMemoryAddress(stackAddress);
    }

    @Override
    public void visit(FloatNum node) {
        loadXRegisterWithConst(toFixedPoint(node.getValue()));
    }

    @Override
    public void visit(Id node) {
        // Man skriver $01 foran adressen fordi den metode man kalder efter er 8-bit og derfor kun 2 decimaler.
        // Når vi skriver $01 foran ender vi i stacken.
        codeBuilder.append(InstructionSet.LDX.getInstruction() + " $01" + Integer.toHexString(symbolTables.get(getScopeLevel()).lookup(node.getName()).getMemoryAddress()) + "\n");
    }

    @Override
    public void visit(IfStmt node) {
        int label = labelCount;
        labelCount++;
        node.getCondition().accept(this);
        binOperatorCount = 0;
        if (node.getCondition() instanceof Bool) {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        } else if (node.getCondition() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append(InstructionSet.CMP.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.BNE.getInstruction() + " end" + label + "\n");
        codeBuilder.append("ifthen" + label + ":\n");
        node.getThenBlock().accept(this);
        codeBuilder.append("end" + label + ":\n");
    }

    @Override
    public void visit(IfElseStmt node) {
        int label = labelCount;
        labelCount++;
        node.getCondition().accept(this);
        binOperatorCount = 0;
        if (node.getCondition() instanceof Bool) {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        } else if (node.getCondition() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append(InstructionSet.CMP.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.BNE.getInstruction() + " else" + label + "\n");
        codeBuilder.append("ifthen" + label + ":\n");
        node.getThenBlock().accept(this);
        codeBuilder.append(InstructionSet.JMP.getInstruction() + " end" + label + "\n");
        codeBuilder.append("else" + label + ":\n");
        node.getElseBlock().accept(this);
        codeBuilder.append("end" + label + ":\n");
    }

    @Override
    public void visit(IntDcl node) {
        Symbol symbol = symbolTables.get(getScopeLevel()).lookup(node.getId());
        symbol.setMemoryAddress(stackAddress);
    }

    @Override
    public void visit(IntNum node) {
        loadXRegisterWithConst(node.getValue());
    }

    @Override
    public void visit(NegationOp node) {
        node.getExpression().accept(this);
        codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        codeBuilder.append(InstructionSet.EOR.getInstruction() + " #1\n");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
        codeBuilder.append(InstructionSet.BRK.getInstruction() + "\n");
    }

    @Override
    public void visit(WhileLoop node) {
        codeBuilder.append("while" + whileLoopCount + ":\n");
        node.getCondition().accept(this);
        if (node.getCondition() instanceof Bool) {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        } else if (node.getCondition() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append(InstructionSet.CMP.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.BNE.getInstruction() + " end" + labelCount + "\n");
        node.getBlock().accept(this);
        codeBuilder.append(InstructionSet.JMP.getInstruction() + " while" + whileLoopCount + "\n");
        whileLoopCount++;
        codeBuilder.append("end" + labelCount + ":\n");
        labelCount++;
    }

    public void addTwoNumbers(ArithmeticOp node) {
        codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");

        if (node.getOperator().equals("+")) {
            codeBuilder.append(InstructionSet.ADC.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
        } else {
            codeBuilder.append(InstructionSet.SBC.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
            codeBuilder.append(InstructionSet.BCS.getInstruction() + " carry" + arithmeticOpCount + "\n");
            codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
            codeBuilder.append("carry" + arithmeticOpCount + ":\n");
            codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
            codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
        }
    }

    public void evaluateArithmeticOp(ArithmeticOp node) {
        boolean isLeftOperandArithmeticOp = node.getLeftOperand() instanceof ArithmeticOp;
        boolean isRightOperandArithmeticOp = node.getRightOperand() instanceof ArithmeticOp;

        if (isLeftOperandArithmeticOp) {
            if (!isRightOperandArithmeticOp) {
                operators.add(node.getOperator());
            }
            evaluateParentheses((ArithmeticOp) node.getLeftOperand());
            codeBuilder.append(InstructionSet.STA.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) +"\n");
            arithmeticOpCount++;
            node.getRightOperand().accept(this);
            if (!isRightOperandArithmeticOp) {
                codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
                arithmeticOpCount++;
            }
        } else {
            operators.add(node.getOperator());
            node.getLeftOperand().accept(this);
            if (!(isLeftOperandArithmeticOp)) {
                codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) +"\n");
                arithmeticOpCount++;
            }
            node.getRightOperand().accept(this);
            if (!(isRightOperandArithmeticOp)) {
                codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
                arithmeticOpCount++;
            }
        }
    }

    public void evaluateParentheses(ArithmeticOp node) {
        node.getLeftOperand().accept(this);
        codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        node.getRightOperand().accept(this);
        addTwoNumbers(node);
    }

    public void clearTheBottomOfStackForArithmeticOp() {
        codeBuilder.append(InstructionSet.LDA.getInstruction() + " $0100\n");
        int i = arithmeticOpCount;
        while (arithmeticOpCount > 1 ){
            if (operators.get(i - arithmeticOpCount).equals("+")) {
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " $01" + String.format("%02d", (i - arithmeticOpCount + 1)) + "\n");
            } else {
                codeBuilder.append(InstructionSet.SBC.getInstruction() + " $01" + String.format("%02d", (i - arithmeticOpCount + 1)) + "\n");
                codeBuilder.append(InstructionSet.BCS.getInstruction() + " carry" + arithmeticOpCount + "\n");
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
                codeBuilder.append("carry" + arithmeticOpCount + ":\n");
                codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
            }
            arithmeticOpCount--;
        }
        operators.clear();
        arithmeticOpCount = 0;
    }

    public void evaluateBinOperator(ComparisonOp node){
        node.getLeftOperand().accept(this);
        if (node.getLeftOperand() instanceof Id ||
            node.getLeftOperand() instanceof IntNum ||
            node.getLeftOperand() instanceof FloatNum ||
            node.getLeftOperand() instanceof Bool)
        {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
            pushAccumulator();
        } else if (node.getLeftOperand() instanceof ArithmeticOp) {
            clearTheBottomOfStackForArithmeticOp();
            pushAccumulator();
        }
        node.getRightOperand().accept(this);
        if (node.getRightOperand() instanceof ArithmeticOp) {
            clearTheBottomOfStackForArithmeticOp();
            codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
            pullAccumulator();
        } else if ((node.getOperator().equals("||") || node.getOperator().equals("&&"))
                && binOperatorCount > 0
                && !(node.getRightOperand() instanceof Bool)
                && !(node.getRightOperand() instanceof NegationOp))
        {
            pullAccumulator();
            codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
            pullAccumulator();
        } else {
            pullAccumulator();
        }
        compareTwoBooleans(node);
    }

    public void compareTwoBooleans(ComparisonOp node) {
        codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
        codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
        switch (node.getOperator()) {
            case "==" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BNE.getInstruction() + " false" + labelCount + "\n");
            }
            case "!=" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BEQ.getInstruction() + " false" + labelCount + "\n");
            }
            case "||" -> {
                codeBuilder.append(InstructionSet.ORA.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BEQ.getInstruction() + " false" + labelCount + "\n");
            }
            case "&&" -> {
                codeBuilder.append(InstructionSet.AND.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BEQ.getInstruction() + " false" + labelCount + "\n");
            }
            case "<" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BEQ.getInstruction() + " false" + labelCount + "\n");
                codeBuilder.append(InstructionSet.BCS.getInstruction() + " false" + labelCount + "\n");
            }
            case "<=" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BCS.getInstruction() + " false" + labelCount + "\n");
            }
            case ">" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BEQ.getInstruction() + " false" + labelCount + "\n");
                codeBuilder.append(InstructionSet.BCC.getInstruction() + " false" + labelCount + "\n");
            }
            case ">=" -> {
                codeBuilder.append(InstructionSet.CMP.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
                codeBuilder.append(InstructionSet.BCC.getInstruction() + " false" + labelCount + "\n");
            }
        }
        codeBuilder.append(InstructionSet.LDA.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.JMP.getInstruction() + " store" + labelCount + "\n");
        codeBuilder.append("false" + labelCount + ":\n");
        codeBuilder.append(" " + InstructionSet.LDA.getInstruction() + " #0\n");
        codeBuilder.append("store" + labelCount + ":\n");
        binOperatorCount++;
        codeBuilder.append("  "); // gør at der kommer indent.
        pushAccumulator();
    }
}
