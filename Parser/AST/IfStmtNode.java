package AST;

import java.util.ArrayList;

public class IfStmtNode extends Node {
    private ArrayList<Node> children;

    public IfStmtNode(IfNode ifNode, LParenNode lParen, ConditionNode condition, RParenNode rParen, LBraceNode lBrace, StmtNode stmt, RBraceNode rBrace, ElseNode elseNode, LBraceNode lBrace2, StmtNode stmt2, RBraceNode rBrace2) {
        this.children = new ArrayList<>();

        if (ifNode != null) {
            this.addChild(ifNode);
        }
        if (lParen != null) {
            this.addChild(lParen);
        }
        if (condition != null) {
            this.addChild(condition);
        }
        if (rParen != null) {
            this.addChild(rParen);
        }
        if (lBrace != null) {
            this.addChild(lBrace);
        }
        if (stmt != null) {
            this.addChild(stmt);
        }
        if (rBrace != null) {
            this.addChild(rBrace);
        }
        if (elseNode != null) {
            this.addChild(elseNode);
        }
        if (lBrace2 != null) {
            this.addChild(lBrace2);
        }
        if (stmt2 != null) {
            this.addChild(stmt2);
        }
        if (rBrace2 != null) {
            this.addChild(rBrace2);
        }
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public void accept(Visitor v) {

    }
}
