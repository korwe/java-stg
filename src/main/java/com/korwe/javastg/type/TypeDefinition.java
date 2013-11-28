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

    @Override
    public boolean canAssign(TypeValue value){
        return value != null && canAssign(value.getType());
    }

}
