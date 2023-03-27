package AST;

public class SymbolTableFilling implements Visitor {

    @Override
    public void visit(Assigning node) {
        node.child1.accept(this);
        node.child2.accept(this);
    }

    @Override
    public void visit(BinOperator node) {

    }

    @Override
    public void visit(Block node) {
        for(Node n : node.children){
            n.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(BoolDcl node) {
        if (Node.SymbolTable.get(node.id) == null) {
            Node.SymbolTable.put(node.id,Node.BOOLTYPE);
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public void visit(Computing node) {

    }

    @Override
    public void visit(FloatDcl node) {
        if (Node.SymbolTable.get(node.id) == null) {
            Node.SymbolTable.put(node.id,Node.FLTTYPE);
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {

    }

    @Override
    public void visit(If node) {
        node.child1.accept(this);
    }

    @Override
    public void visit(IfElse node) {
        node.child1.accept(this);
        node.child2.accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        if (Node.SymbolTable.get(node.id) == null) {
            Node.SymbolTable.put(node.id,Node.INTTYPE);
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(Not node) {

    }

    @Override
    public void visit(Print node) {

    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.println();
    }

    private void error(String message) {
        throw new Error(message);
    }

}
