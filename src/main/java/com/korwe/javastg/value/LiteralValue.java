package com.korwe.javastg.value;

import com.korwe.javastg.exception.UnsupportedTypeException;
import com.korwe.javastg.type.BoxableType;
import com.korwe.javastg.type.PrimitiveType;
import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class LiteralValue extends TypeDefinitionValue{
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
            super.setTypeDefinition(BoxableType.String);
            this.value = String.format("\"%s\"",s);
        }
        else{
            this.value =  s;
        }
    }

    public void setValue(Character c){
        super.setTypeDefinition(PrimitiveType.Char);
        this.value = "'"+String.valueOf(c)+"'";
    }

    public void setValue(Boolean b){
        super.setTypeDefinition(PrimitiveType.Boolean);
        this.value = String.valueOf(b);
    }

    public void setValue(Byte b){
        super.setTypeDefinition(PrimitiveType.Byte);
        this.value = "0x"+Integer.toHexString(Integer.valueOf(String.valueOf(b)));
    }

    public void setValue(Short s){
        super.setTypeDefinition(PrimitiveType.Short);
        this.value = String.valueOf(s);
    }

    public void setValue(Integer i){
        super.setTypeDefinition(PrimitiveType.Int);
        this.value = String.valueOf(i);
    }

    public void setValue(Long l){
        super.setTypeDefinition(PrimitiveType.Long);
        this.value = String.valueOf(l)+"L";
    }

    public void setValue(Float f){
        super.setTypeDefinition(PrimitiveType.Float);
        this.value = String.valueOf(f)+"f";
    }

    public void setValue(Double d){
        super.setTypeDefinition(PrimitiveType.Double);
        this.value = String.valueOf(d);
    }

    @Override
    public void setTypeDefinition(TypeDefinition typeDefinition){
        if(!typeDefinition.hasLiteralSupport()){
            throw new UnsupportedTypeException();
        }
        super.setTypeDefinition(typeDefinition);
    }


}
