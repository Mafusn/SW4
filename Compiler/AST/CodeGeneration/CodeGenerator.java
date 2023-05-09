package AST.CodeGeneration;

import AST.Nodes.*;
import AST.OperationSet;
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
    private int stackAddress = 0;
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
        codeBuilder.append(InstructionSet.PHA.getInstruction() + "\n");
        incrementStackAddress();
    }

    private void pullAccumulator() {
        codeBuilder.append(InstructionSet.PLA.getInstruction() + "\n");
        decrementStackAddress();
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
        Symbol symbol = symbolTables.get(getScopeLevel()).lookup(id);
        codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        codeBuilder.append(InstructionSet.TSX.getInstruction() + "\n");
        for (int i = 0; i < stackAddress - symbol.getMemoryAddress(); i++) {
            codeBuilder.append(InstructionSet.INX.getInstruction() + "\n");
        }
        codeBuilder.append(InstructionSet.STA.getInstruction() + " $0100, x" + "\n");
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
        // If statement som tjekker om det er en compound assignment.
        if (node.getCompAssOp() == null) {
            // If statement som tjekker for om det er en ny dekleration
            if (node.getLeft() instanceof Id) {
                node.getRight().accept(this);
                if (node.getRight() instanceof ArithmeticOp) {
                    clearTheBottomOfStackForArithmeticOp();
                }
                storeXRegisterInVariable(node.getVariable());
            } else {
                node.getRight().accept(this);
                if (node.getRight() instanceof ArithmeticOp) {
                    clearTheBottomOfStackForArithmeticOp();
                } else {
                    codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
                }
                node.getLeft().accept(this);
                pushAccumulator();
            }
        } else {
            if (node.getRight() instanceof ArithmeticOp) {
                node.getLeft().accept(this);
                codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
                arithmeticOpCount++;
                if (node.getCompAssOp().equals(OperationSet.COMPASSPLUS.getOp())) {
                    operators.add("+");
                } else if (node.getCompAssOp().equals(OperationSet.COMPASSMINUS.getOp())) {
                    operators.add("-");
                } else {
                    RuntimeException e = new RuntimeException("invalid operator");
                    System.out.println(node.getVariable() + ": has an " + e);
                }
                node.getRight().accept(this);
                clearTheBottomOfStackForArithmeticOp();
            } else {
                node.getLeft().accept(this);
                codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
                node.getRight().accept(this);
                codeBuilder.append(InstructionSet.STX.getInstruction() + " $0100\n");
                // Alt det her er blot til at plusse eller minus variablen med en konstant.
                if (node.getCompAssOp().equals("+=")) {
                    codeBuilder.append(InstructionSet.ADC.getInstruction() + " $0100\n");
                } else {
                    codeBuilder.append(InstructionSet.SBC.getInstruction() + " $0100\n");
                    codeBuilder.append(InstructionSet.BCS.getInstruction() + " carry" + labelCount + "\n");
                    codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
                    codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
                    codeBuilder.append(InstructionSet.JMP.getInstruction() + " carryend" + labelCount + "\n");
                    codeBuilder.append("carry" + labelCount + ":\n");
                    codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
                    codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
                    codeBuilder.append("carryend" + labelCount + ":\n");
                    labelCount++;
                }
            }
            codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
            storeXRegisterInVariable(node.getVariable());
        }
    }

    @Override
    public void visit(ComparisonOp node) {
        node.getLeft().accept(this);
        // Hele det her if-statement tjekker for om venstre er alt andet end en comparison node.
        if (node.getLeft() instanceof Id ||
                node.getLeft() instanceof IntNum ||
                node.getLeft() instanceof FloatNum ||
                node.getLeft() instanceof Bool)
        {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
            pushAccumulator();
        } else if (node.getLeft() instanceof ArithmeticOp) {
            clearTheBottomOfStackForArithmeticOp();
            pushAccumulator();
        }
        node.getRight().accept(this);
        if (node.getRight() instanceof ArithmeticOp) {
            clearTheBottomOfStackForArithmeticOp();
            codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
        }
        pullAccumulator();
        if (binOperatorCount > 0 && (node.getOperator().equals("&&") || node.getOperator().equals("||")) && !(node.getRight() instanceof Bool)) {
            codeBuilder.append(InstructionSet.TAX.getInstruction() + "\n");
            pullAccumulator();
        }
        compareTwoBooleans(node.getOperator());
    }

    @Override
    public void visit(Block node) {
        blockCount++;
        int stackAddressPlaceHolder = stackAddress;
        scopeLevel++;
        for (Node n : node.getChildren()) {
            n.accept(this);
        }
        for (int i = stackAddress - stackAddressPlaceHolder; i > 0; i--) {
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
        node.getLeft().accept(this);
        if (!(node.getLeft() instanceof ArithmeticOp)) {
            codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
            arithmeticOpCount++;
        }
        node.getRight().accept(this);
        codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", arithmeticOpCount) + "\n");
        arithmeticOpCount++;
        operators.add(node.getOperator());
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
        codeBuilder.append(InstructionSet.TSX.getInstruction() + "\n");
        for (int i = 0; i < stackAddress - symbolTables.get(getScopeLevel()).lookup(node.getName()).getMemoryAddress(); i++) {
            codeBuilder.append(InstructionSet.INX.getInstruction() + "\n");
        }
        codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        codeBuilder.append(InstructionSet.TAY.getInstruction() + "\n");
        codeBuilder.append(InstructionSet.LDX.getInstruction() + " $0100, y" + "\n");
    }

    @Override
    public void visit(IfStmt node) {
        int label = labelCount;
        labelCount++;
        node.getLeft().accept(this);
        binOperatorCount = 0;
        if (node.getLeft() instanceof Bool) {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        } else if (node.getLeft() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append(InstructionSet.CMP.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.BNE.getInstruction() + " end" + label + "\n");
        codeBuilder.append("ifthen" + label + ":\n");
        node.getRight().accept(this);
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
        node.getLeft().accept(this);
        codeBuilder.append(InstructionSet.JMP.getInstruction() + " end" + label + "\n");
        codeBuilder.append("else" + label + ":\n");
        node.getRight().accept(this);
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
        node.getLeft().accept(this);
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
    public void visit(PointerDcl node) {
        /*int num = 255; // sample integer
        String hex = String.format("%04X", num); // convert to 4-digit hexadecimal string
        System.out.println(hex); // prints "00FF"*/
    }

    public void visit(WhileLoop node) {
        codeBuilder.append("while" + whileLoopCount + ":\n");
        node.getLeft().accept(this);
        if (node.getLeft() instanceof Bool) {
            codeBuilder.append(InstructionSet.TXA.getInstruction() + "\n");
        } else if (node.getLeft() instanceof Id) {

        } else {
            pullAccumulator();
        }
        codeBuilder.append(InstructionSet.CMP.getInstruction() + " #1\n");
        codeBuilder.append(InstructionSet.BNE.getInstruction() + " end" + labelCount + "\n");
        node.getRight().accept(this);
        codeBuilder.append(InstructionSet.JMP.getInstruction() + " while" + whileLoopCount + "\n");
        whileLoopCount++;
        codeBuilder.append("end" + labelCount + ":\n");
        labelCount++;
    }

    public void clearTheBottomOfStackForArithmeticOp() {
        codeBuilder.append(InstructionSet.LDA.getInstruction() + " $0100\n");
        int i = arithmeticOpCount;
        while (arithmeticOpCount > 1 ) {
            if (operators.get(i - arithmeticOpCount).equals("+")) {
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " $01" + String.format("%02d", (i - arithmeticOpCount + 1)) + "\n");
            } else {
                codeBuilder.append(InstructionSet.SBC.getInstruction() + " $01" + String.format("%02d", (i - arithmeticOpCount + 1)) + "\n");
                codeBuilder.append(InstructionSet.BCS.getInstruction() + " carry" + labelCount + "\n");
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
                codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
                codeBuilder.append(InstructionSet.JMP.getInstruction() + " carryend" + labelCount + "\n");
                codeBuilder.append("carry" + labelCount + ":\n");
                codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
                codeBuilder.append(InstructionSet.ADC.getInstruction() + " #1\n");
                codeBuilder.append("carryend" + labelCount + ":\n");
                labelCount++;
            }
            arithmeticOpCount--;
        }
        operators.clear();
        arithmeticOpCount = 0;
    }

    public void compareTwoBooleans(String operator) {
        codeBuilder.append(InstructionSet.STX.getInstruction() + " $01" + String.format("%02d", binOperatorCount) + "\n");
        codeBuilder.append(InstructionSet.CLC.getInstruction() + "\n");
        switch (operator) {
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
        pushAccumulator();
        labelCount++;
        binOperatorCount++;
    }
}
