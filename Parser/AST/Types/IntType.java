package AST.Types;

import AST.BinOperator;

public class IntType extends Type{
    public static final IntType INSTANCE = new IntType();

    public IntType() {}

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
            switch (operator) {
                case "+":
                case "-":
                    return IntType.INSTANCE;
                // Comparison operators return a boolean result
                case "<":
                case "<=":
                case ">":
                case ">=":
                case "==":
                case "!=":
                    return BooleanType.INSTANCE;
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
}
