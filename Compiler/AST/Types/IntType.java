package AST.Types;

import AST.Nodes.IntNum;

public class IntType extends Type{
    public static final IntType INSTANCE = new IntType();

    public IntType() {}

    public static IntType getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isAssignable(Type other) {
        // An int is assignable to another int
        return other instanceof IntType;
    }

    @Override
    public boolean isEqual(Type other) {
        // An int is equal to another int
        return other instanceof IntType;
    }

    @Override
    public Type getResultType(String operator, Type other) {
        if (other instanceof IntType) {
            // Arithmetic operators return an int result when both operands are ints
            return switch (operator) {
                case "+", "-" -> IntType.INSTANCE;
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
        IntType intType = (IntType) o;
        return this.isEqual(intType);
    }
}
