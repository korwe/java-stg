package com.korwe.javastg.value;

import com.korwe.javastg.exception.NoLiteralSupportException;
import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class LiteralValue extends TypeDefinitionValue{
    String value;

    public LiteralValue(TypeDefinition typeDefinition) {
        super(typeDefinition);
    }

    @Override
    public String getCodeString() {
        return value;
    }

    public LiteralValue(TypeDefinition typeDefinition, String value){
        super(typeDefinition);
        this.value = value;
    }

    public LiteralValue(TypeDefinition typeDefinition, Object value){
        super(typeDefinition);
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(Object value){
        setValue(value, false);
    }

    public void setValue(Object value, boolean isString){
        if(!getTypeDefinition().hasLiteralSupport()){
            throw new NoLiteralSupportException();
        }

        if(String.class.isAssignableFrom(value.getClass())){
            if(isString){
                this.value = '"'+(String)value+'"';
            }
            else{
                this.value = (String)value;
            }
        }
        else if(Character.class.isAssignableFrom(value.getClass())){
            this.value = "'"+String.valueOf(value)+"'";
        }
        else if(Boolean.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value);
        }
        else if(Byte.class.isAssignableFrom(value.getClass())){
            //print as hex
            this.value = "0x"+Integer.toHexString(Integer.valueOf(String.valueOf(value)));
        }
        else if(Short.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value);
        }
        else if(Integer.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value);
        }
        else if(Long.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value)+"L";
        }
        else if(Float.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value)+"f";
        }
        else if(Double.class.isAssignableFrom(value.getClass())){
            this.value = String.valueOf(value);
        }
        else{
            throw new NoLiteralSupportException();
        }
    }


}
