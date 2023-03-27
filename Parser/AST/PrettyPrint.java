package AST;

public class PrettyPrint implements Visitor {
    @Override
    public void visit(Assigning node) {
        node.child1.accept(this);
        System.out.print("= ");
        node.child2.accept(this);
    }

    @Override
    public void visit(BinOperator node) {
        node.child1.accept(this);
        System.out.print(node.operation + " ");
        node.child2.accept(this);
    }

    @Override
    public void visit(Block node) {
        System.out.print("block ");
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.print("block-end ");
    }

    @Override
    public void visit(Bool node) {
        System.out.print(node.value);
        System.out.print(" ");
    }

    @Override
    public void visit(BoolDcl node) {
        System.out.print("boolean " + node.id);
        System.out.print(" ");
    }

    @Override
    public void visit(Computing node) {
        node.child1.accept(this);
        System.out.print(node.operation + " ");
        node.child2.accept(this);
    }

    @Override
    public void visit(FloatDcl node) {
        System.out.print("float " + node.id);
        System.out.print(" ");
    }

    @Override
    public void visit(FloatNum node) {
        System.out.print(node.value);
        System.out.print(" ");
    }

    @Override
    public void visit(Id node) {
        System.out.print(node.id);
        System.out.print(" ");
    }

    @Override
    public void visit(If node) {
        System.out.print("if ");
        node.condition.accept(this);
        node.child1.accept(this);
    }

    @Override
    public void visit(IfElse node) {
        System.out.print("if ");
        node.condition.accept(this);
        node.child1.accept(this);
        System.out.print("else ");
        node.child2.accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        System.out.print("int " + node.id);
        System.out.print(" ");
    }

    @Override
    public void visit(IntNum node) {
        System.out.print(node.value);
        System.out.print(" ");
    }

    @Override
    public void visit(Not node) {
        System.out.print("not ");
        node.child.accept(this);
    }

    @Override
    public void visit(Print node) {
        System.out.print("print " + node.id);
        System.out.print(" ");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.println();
    }
}
