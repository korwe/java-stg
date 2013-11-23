package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeDefinitionValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class TypeDefinition extends Annotatable{
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

    public boolean isGenerifiedType(){
        return GenerifiableType.class.isAssignableFrom(this.getClass());
    }

    public boolean hasLiteralSupport(){
        return isPrimitiveType();
    }

    public boolean isCompatibleWith(TypeDefinitionValue value){
        if(this.isPrimitiveType()){
            //this is primitive so doesn't support null
            if(value == null){
                return false;
            }
            else{
                if(PrimitiveType.class.isAssignableFrom(value.getTypeDefinition().getClass())){
                    //Check if the types are the same
                    if(value.getTypeDefinition().equals(this)){
                        return true;
                    }
                    else{
                        //Check boxable type is the same
                        return ((PrimitiveType)this).getBoxableType().equals(((PrimitiveType)value.getTypeDefinition()).getBoxableType());
                    }
                }
                else{
                    return value.getTypeDefinition().equals(((PrimitiveType)this).getBoxableType());
                }
            }

        }
        else{
            if(value == null){
                return true;       //Reference types support null
            }
            else{
                if(value.getTypeDefinition().isPrimitiveType()){
                    return equals(((PrimitiveType)value.getTypeDefinition()).getBoxableType()); //is boxable and compatible
                }
                else{
                    return equals(value.getTypeDefinition()); //same types
                }
            }
        }

    }

}
