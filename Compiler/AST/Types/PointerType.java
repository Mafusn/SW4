package AST.Types;

public class PointerType extends Type {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointerType pointerType = (PointerType) o;
        return this.isEqual(pointerType);
    }
}
