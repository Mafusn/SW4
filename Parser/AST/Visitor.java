package AST;

import AST.Types.Type;

public interface Visitor {
    void visit(Assigning node);
    Type visit(BinOperator node);
    void visit(Block node);
    void visit(Bool node);
    void visit(BoolDcl node);
    Type visit(Computing node);
    void visit(FloatDcl node);
    void visit(FloatNum node);
    Type visit(Id node);
    void visit(If node);
    void visit(IfElse node);
    void visit(IntDcl node);
    void visit(IntNum node);
    Type visit(Not node);
    void visit(Print node);
    void visit(Prog node);
}
