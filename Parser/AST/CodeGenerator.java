package AST;

import AST.*;
import AST.Types.Type;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator implements Visitor {
    private StringBuilder codeBuilder;
    private SymbolTableFilling symbolTable;
    private int stackAddress = 0xff;

    public void incrementStack(){
        stackAddress++;
    }

    public CodeGenerator(SymbolTableFilling symbolTable) throws IOException {
        codeBuilder = new StringBuilder();
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
        //initializing stack-pointer
        codeBuilder.append("LDX #$" + Integer.toHexString(stackAddress).toUpperCase() + "\n");
        incrementStack();
        incrementStack();
        codeBuilder.append("TXS\n");
        node.child2.accept(this);
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

    }

    @Override
    public void visit(BoolDcl node) {

    }

    @Override
    public Type visit(Computing node) {
        return null;
    }

    @Override
    public void visit(FloatDcl node) {

    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public Type visit(Id node) {
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

    }

    @Override
    public void visit(IntNum node) {
        codeBuilder.append("INX\n");
        codeBuilder.append("INX\n");
        codeBuilder.append("LDA #" + node.value + "\n");
        codeBuilder.append("STA $0100,X\n");
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
    }
}
