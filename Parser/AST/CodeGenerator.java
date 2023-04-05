package AST;

import AST.Types.Type;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeGenerator implements Visitor {
    private StringBuilder codeBuilder;
    private SymbolTableFilling symbolTable;
    private int stackAddress = 0xff;
    private int computingCount = 0;
    private String previousOperator = "";

    private void pushAccumulator() {
        codeBuilder.append("PHA\n");
        stackAddress--;
    }

    private void pullAccumulator() {
        codeBuilder.append("PLA\n");
        stackAddress++;
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
        codeBuilder.append("PHA\n");
    }

    @Override
    public Type visit(BinOperator node) {
        return null;
    }

    @Override
    public void visit(Block node) {

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
        stackAddress--;
    }

    @Override
    public Type visit(Computing node) {
        node.getLeftOperand().accept(this);
        if (computingCount > 0) {
            codeBuilder.append("STX $0100\n");
            codeBuilder.append("CLC\n");
            if (previousOperator.equals("+")) {
                codeBuilder.append("ADC $0100\n");
            }
            else {
                codeBuilder.append("SBC $0100\n");
                codeBuilder.append("EOR #$FF\n");
            }
            previousOperator = node.getOperator();
        } else {
            codeBuilder.append("TXA\n");
            previousOperator = node.getOperator();
        }
        computingCount++;
        node.getRightOperand().accept(this);
        if (node.getRightOperand() instanceof Computing) {
            return null;
        } else {
            codeBuilder.append("STX $0100\n");
            codeBuilder.append("CLC\n");
            if (node.getOperator().equals("+")) {
                codeBuilder.append("ADC $0100\n");
            } else {
                codeBuilder.append("SBC $0100\n");
                codeBuilder.append("EOR #$FF\n");
            }
        }

        return null;
    }

    @Override
    public void visit(FloatDcl node) {
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        stackAddress--;
        stackAddress--;
    }

    @Override
    public void visit(FloatNum node) {
        // .split("\\.") splits the string at the dot into an array with two entries.
        String[] parts = node.value.split("\\.");
        loadXRegisterWithConst(Integer.parseInt(parts[0]));
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

    }

    @Override
    public void visit(IfElse node) {

    }

    @Override
    public void visit(IntDcl node) {
        Symbol symbol = symbolTable.lookup(node.id);
        symbol.setMemoryAddress(stackAddress);
        stackAddress--;
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
    }
}
