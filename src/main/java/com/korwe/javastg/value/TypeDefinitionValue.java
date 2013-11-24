package com.korwe.javastg.value;

import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class TypeDefinitionValue {
    private TypeDefinition typeDefinition;

    public TypeDefinitionValue(){}

    public TypeDefinitionValue(TypeDefinition typeDefinition){
        this.typeDefinition = typeDefinition;
    }

    public TypeDefinition getTypeDefinition() {
        return typeDefinition;
    }

    public void setTypeDefinition(TypeDefinition typeDefinition) {
        this.typeDefinition = typeDefinition;
    }

    public boolean isLiteralValue(){
        return LiteralValue.class.isAssignableFrom(getClass());
    }

    public boolean isArrayValue(){
        return ArrayValue.class.isAssignableFrom(getClass());
    }

    public boolean isArrayWithValues(){
        return ArrayWithValues.class.isAssignableFrom(getClass());
    }

    public boolean isConstructorValue(){
        return ConstructorValue.class.isAssignableFrom(getClass());
    }

    public abstract String getCodeString();

    @Override
    public String toString(){
        return getCodeString();
    }
}

