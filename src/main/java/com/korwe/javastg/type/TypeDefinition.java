package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class TypeDefinition {
    private final String name;

    public TypeDefinition(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimitiveType(){
        return PrimitiveType.class.isAssignableFrom(this.getClass());
    }

    public boolean isReferenceType(){
        return ReferenceType.class.isAssignableFrom(this.getClass());
    }
}
