package AST;

public interface Visitor {
    void visit(ProgNode v);
    void visit(DclNode v);
    void visit(TypeNode v);
    void visit(StmtNode v);
    void visit(ExprNode v);
    void visit(PrintStmtNode v);
    void visit(ValNode v);
    void visit(IfStmtNode v);
    void visit(ConditionNode v);
    void visit(ConditionTailNode v);
    void visit(ComparisonNode v);
    void visit(ComparisonTailNode v);
    void visit(TermNode v);
    void visit(TermTailNode v);
    void visit(FactorNode v);

    /* Terminal Node */

    void visit(LParenNode v);
    void visit(RParenNode v);
    void visit(LBraceNode v);
    void visit(RBraceNode v);
    void visit(PrintNode v);
    void visit(ANDNode v);
    void visit(ORNode v);
    void visit(EQNode v);
    void visit(NENode v);
    void visit(GTNode v);
    void visit(LTNode v);
    void visit(GENode v);
    void visit(LENode v);
    void visit(MinusNode v);
    void visit(PlusNode v);
    void visit(IfNode v);
    void visit(ElseNode v);
    void visit(IntDclNode v);
    void visit(FloatDclNode v);
    void visit(BoolDclNode v);
    void visit(IntNode v);
    void visit(FloatNode v);
    void visit(TrueNode v);
    void visit(FalseNode v);
    void visit(IdNode v);
    void visit(AssignNode v);
    void visit(EOLNode v);
    void visit(EOFNode v);
}
