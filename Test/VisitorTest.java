import AST.Nodes.*;
import AST.Visitor;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest implements Visitor {

    @Test
    void VisitorProgTest() {
        Prog prog = new Prog();
        prog.accept(this);

        //assert(this.equals(prog));
    }

    @Test
    void VisitorArithmeticOpTest() {
        Prog prog = new Prog();

        prog.accept(this);
    }

    @Override
    public void visit(AssignmentOp node) {

    }

    @Override
    public void visit(ComparisonOp node) {

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
    public void visit(ArithmeticOp node) {

    }

    @Override
    public void visit(FloatDcl node) {

    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {

    }

    @Override
    public void visit(IfStmt node) {

    }

    @Override
    public void visit(IfElseStmt node) {

    }

    @Override
    public void visit(IntDcl node) {

    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(NegationOp node) {

    }

    @Override
    public void visit(Prog node) {
        System.out.println("This works.");
    }

    @Override
    public void visit(WhileLoop node) {

    }
}
