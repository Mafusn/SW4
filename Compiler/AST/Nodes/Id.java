package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class Id extends Node {
    private String id;
    private Type type;
    private boolean isAdressRef;
    private boolean isPointer;
    private String adress;

    public Id(String id, boolean isAdressRef, boolean isPointer){
        this.id = id;
        this.isAdressRef = isAdressRef;
        this.isPointer = isPointer;
    }

    public boolean isAdressRef() {
        return isAdressRef;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setAdressRef(boolean adressRef) {
        isAdressRef = adressRef;
    }

    public void setPointer(boolean pointer) {
        isPointer = pointer;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            setType(symbolTable.lookup(id).getType());
        }
        return this.type;
    }

    public String getName() {
        return id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id idNode = (Id) o;
        return isAdressRef == idNode.isAdressRef && isPointer == idNode.isPointer && id.equals(idNode.id);
    }
}
