package AST.Types;

import AST.OperationSet;

import java.lang.management.OperatingSystemMXBean;

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
            if (operator.equals(OperationSet.PLUS.getOperation()) || operator.equals(OperationSet.MINUS.getOperation())) {
                return FloatType.INSTANCE;
            } else if (operator.equals(OperationSet.LESSTHAN.getOperation()) || operator.equals(OperationSet.LESSEQUAL.getOperation()) ||
                operator.equals(OperationSet.GREATERTHAN.getOperation()) || operator.equals(OperationSet.GREATEREQUAL.getOperation()) ||
                operator.equals(OperationSet.EQUAL.getOperation()) || operator.equals(OperationSet.NOTEQUAL.getOperation())) {
                return BooleanType.INSTANCE;
            }
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
