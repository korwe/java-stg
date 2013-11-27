package com.korwe.javastg.value;

import com.korwe.javastg.type.Type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class TypeValue {
    private Type type;

    public TypeValue(){}

    public TypeValue(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public boolean isEnumConstructorValue() {
        return EnumConstructorValue.class.isAssignableFrom(getClass());
    }

    public abstract String getCodeString();

    @Override
    public String toString(){
        return getCodeString();
    }

}

