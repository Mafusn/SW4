package AST;

import AST.Types.Type;

public class Symbol {
    private String name;
    private Type type;
    private int scopeLevel;

    public Symbol(String name, Type type, int scopeLevel) {
        this.name = name;
        this.type = type;
        this.scopeLevel = scopeLevel;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getScopeLevel() {
        return scopeLevel;
    }
}
