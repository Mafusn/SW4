package AST;

import java.util.ArrayList;

public class DclNode extends Node {
    private final ArrayList<Node> children;
    TypeNode type;
    IdNode id;
    ValNode val;
    AssignNode assign;
    ExprNode expr;

    public DclNode(TypeNode type, IdNode id, ValNode val, AssignNode assign, ExprNode expr) {
        this.children = new ArrayList<>();
        this.type = type;
        this.id = id;
        this.val = val;
        this.assign = assign;
        this.expr = expr;

        if (type != null) {
            this.addChild(type);
        }
        if (id != null) {
            this.addChild(id);
        }
        if (assign != null) {
            this.addChild(assign);
            if (val != null) {
                this.addChild(val);
            }
            if (expr != null) {
                this.addChild(expr);
            }
        }
    }

    public TypeNode getType() {
        return type;
    }

    public IdNode getId() {
        return id;
    }

    public ValNode getVal() {
        return val;
    }

    public AssignNode getAssign() {
        return assign;
    }

    public ExprNode getExpr() {
        return expr;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
