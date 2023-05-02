package AST;

import AST.Nodes.*;

public interface Visitor {
    void visit(Assigning node);
    void visit(BinOperator node);
    void visit(Block node);
    void visit(Bool node);
    void visit(BoolDcl node);
    void visit(Computing node);
    void visit(FloatDcl node);
    void visit(FloatNum node);
    void visit(Id node);
    void visit(If node);
    void visit(IfElse node);
    void visit(IntDcl node);
    void visit(IntNum node);
    void visit(Not node);
    void visit(Print node);
    void visit(Prog node);
    void visit(PointerDcl node);
}
