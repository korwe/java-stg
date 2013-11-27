package com.korwe.javastg.type;

import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.value.TypeValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class TypeDefinition extends Annotatable implements Type {
    private final String name;

    public TypeDefinition(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPrimitiveType(){
        return Primitive.class.isAssignableFrom(this.getClass());
    }

    public boolean hasLiteralSupport(){
        return isPrimitiveType();
    }

    public boolean isCompatibleWith(TypeValue value){
        if(this.isPrimitiveType()){
            //this is primitive so doesn't support null
            if(value == null){
                return false;
            }
            else{
                if(Primitive.class.isAssignableFrom(value.getType().getClass())){
                    //Check if the types are the same
                    if(value.getType().equals(this)){
                        return true;
                    }
                    else{
                        //Check boxable type is the same
                        return ((Primitive)this).getBoxableType().equals(((Primitive)value.getType()).getBoxableType());
                    }
                }
                else{
                    return value.getType().equals(((Primitive)this).getBoxableType());
                }
            }

        }
        else{
            if(value == null){
                return true;       //Reference types support null
            }
            else{
                if(TypeDefinition.class.isAssignableFrom(value.getType().getClass()) && ((TypeDefinition)value.getType()).isPrimitiveType()){
                    return equals(((Primitive)value.getType()).getBoxableType()); //i1s boxable and compatible
                }
                else{
                    return equals(value.getType()); //same types
                }
            }
        }

    }

}
