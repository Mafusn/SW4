package AST.Types;

public class FloatType extends Type{
    public static final FloatType INSTANCE = new FloatType();

    public FloatType() {
        // Make constructor private to prevent creation of multiple instances
    }

    @Override
    public boolean isAssignable(Type other) {
        // A float is assignable to another float or an int
        return other instanceof FloatType;
    }

    @Override
    public boolean isEqual(Type other) {
        // An int is equal to another int
        return other instanceof FloatType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        if (other instanceof FloatType) {
            // Arithmetic operators return an int result when both operands are ints
            return switch (operator) {
                case "+", "-" -> FloatType.INSTANCE;
                // Comparison operators return a boolean result
                case "<", "<=", ">", ">=", "==", "!=" -> BooleanType.INSTANCE;
                default -> null;
            };
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatType FloatType = (FloatType) o;
        return this.isEqual(FloatType);
    }
}
