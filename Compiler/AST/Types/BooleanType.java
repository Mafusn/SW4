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
        switch (operator) {
            case "==":
            case "!=":
            case "<":
            case ">":
            case "<=":
            case ">=":
                return BooleanType.INSTANCE;
            case "&&":
            case "||":
                if (other instanceof BooleanType) {
                    return BooleanType.INSTANCE;
                }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanType booleanType = (BooleanType) o;
        return this.isEqual(booleanType);
    }
}