package AST.Types;

public class ProcedureType extends Type {
    @Override
    public boolean isAssignable(Type other) {
        return false;
    }

    @Override
    public boolean isEqual(Type other) {
        return false;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        return null;
    }
}
