package AST.Types;

public class BooleanType extends Type{
    public static final BooleanType INSTANCE = new BooleanType();

    public BooleanType() {
        // Make constructor private to prevent creation of multiple instances
    }
    @Override
    public boolean isAssignable(Type other) {
        // A boolean is assignable only to another boolean
        return other instanceof BooleanType;
    }

    @Override
    public boolean isEqual(Type other) {
        return other instanceof BooleanType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        if (operator.equals("==") || operator.equals("!=") || operator.equals("<") ||
                operator.equals(">") || operator.equals("<=") || operator.equals(">=")) {
            return BooleanType.INSTANCE;
        } else if (operator.equals("&&") || operator.equals("||")) {
            if (other instanceof BooleanType) {
                return BooleanType.INSTANCE;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}