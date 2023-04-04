package AST;

import AST.*;
import AST.Types.IntType;
import AST.Types.Type;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator implements Visitor {
    private StringBuilder codeBuilder;
    private SymbolTableFilling symbolTable;
    private int stackAddress = 0xff;

    public void decrementStack(){
        System.out.println("decrement stack: " + stackAddress);
        stackAddress--;
    }
    public void incrementStack(){
        System.out.println("increment stack: " + stackAddress);
        stackAddress++;
    }
    public String getStackAddress(){
        return Integer.toHexString(stackAddress);
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
            codeBuilder.append("LDA #1\n");
        } else {
            codeBuilder.append("LDA #0\n");
        }
    }

    @Override
    public void visit(BoolDcl node) {
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        decrementStack();
    }

    @Override
    public Type visit(Computing node) {
        node.getLeftOperand().accept(this);
        codeBuilder.append("TAX\n");
        codeBuilder.append("STX $0100\n");
        node.getRightOperand().accept(this);
            codeBuilder.append("CLC\n");
        if (node.getOperator().equals("+")) {
            codeBuilder.append("ADC $0100\n");
        } else {
            codeBuilder.append("SBC $0100\n");
            codeBuilder.append("EOR #$FF\n");
        }
        return null;
    }

    @Override
    public void visit(FloatDcl node) {
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        decrementStack();
        decrementStack();
    }

    @Override
    public void visit(FloatNum node) {
        // .split("\\.") splits the string at the dot into an array with two entries.
        String[] s = node.value.split("\\.");
        codeBuilder.append("LDA #" + s[0] + "\n");
        codeBuilder.append("PHA\n");
        decrementStack();
        codeBuilder.append("LDA #" + s[1] + "\n");
    }

    @Override
    public Type visit(Id node) {
        // Man skriver $01 foran adressen fordi den metode man kalder efter er 8-bit og derfor kun 2 decimaler.
        // Når vi skriver $01 foran ender vi i stacken.
        codeBuilder.append("LDA $01" + Integer.toHexString(symbolTable.lookup(node.id).getMemoryAddress()) + "\n");
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
        symbolTable.lookup(node.id).setMemoryAddress(stackAddress);
        decrementStack();
    }

    @Override
    public void visit(IntNum node) {
        codeBuilder.append("LDA #" + node.value + "\n");
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
