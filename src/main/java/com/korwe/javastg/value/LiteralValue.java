package com.korwe.javastg.value;

import com.korwe.javastg.definition.Boxable;
import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.exception.UnsupportedTypeException;
import com.korwe.javastg.type.Type;
import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class LiteralValue extends TypeValue {
    String value;

    public LiteralValue(){}

    public LiteralValue(String literalValue){
        super(null);
        value = literalValue;
    }

    public LiteralValue(String value, boolean isString){
        setValue(value, isString);
    }

    public LiteralValue(Character c){
        setValue(c);
    }

    public LiteralValue(Boolean b){
        setValue(b);
    }

    public LiteralValue(Byte b){
        setValue(b);
    }

    public LiteralValue(Short s){
        setValue(s);
    }

    public LiteralValue(Integer i){
        setValue(i);
    }

    public LiteralValue(Long l){
        setValue(l);
    }

    public LiteralValue(Float f){
        setValue(f);
    }

    public LiteralValue(Double d){
        setValue(d);
    }

    @Override
    public String getCodeString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String s, boolean isString){
        if(isString){
            super.setType(Boxable.String);
            this.value = String.format("\"%s\"",s);
        }
        else{
            this.value =  s;
        }
    }

    public void setValue(Character c){
        super.setType(Primitive.Char);
        this.value = "'"+String.valueOf(c)+"'";
    }

    public void setValue(Boolean b){
        super.setType(Primitive.Boolean);
        this.value = String.valueOf(b);
    }

    public void setValue(Byte b){
        super.setType(Primitive.Byte);
        this.value = "0x"+Integer.toHexString(Integer.valueOf(String.valueOf(b)));
    }

    public void setValue(Short s){
        super.setType(Primitive.Short);
        this.value = String.valueOf(s);
    }

    public void setValue(Integer i){
        super.setType(Primitive.Int);
        this.value = String.valueOf(i);
    }

    public void setValue(Long l){
        super.setType(Primitive.Long);
        this.value = String.valueOf(l)+"L";
    }

    public void setValue(Float f){
        super.setType(Primitive.Float);
        this.value = String.valueOf(f)+"f";
    }

    public void setValue(Double d){
        super.setType(Primitive.Double);
        this.value = String.valueOf(d);
    }

    @Override
    public void setType(Type type){
        if(TypeDefinition.class.isAssignableFrom(type.getClass()) && !((TypeDefinition)type).hasLiteralSupport()){
            throw new UnsupportedTypeException();
        }
        super.setType(type);
    }


}
