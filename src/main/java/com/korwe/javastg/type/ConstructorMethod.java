package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConstructorMethod extends Method{
    public ConstructorMethod(AccessModifier accessModifier, TypeDefinition returnType){
        super(accessModifier, null);
        setReturnType(returnType);
    }
}
