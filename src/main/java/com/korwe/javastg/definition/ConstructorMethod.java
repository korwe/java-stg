package com.korwe.javastg.definition;

import com.korwe.javastg.type.Method;
import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConstructorMethod extends Method {
    public ConstructorMethod(AccessModifier accessModifier, TypeDefinition returnType){
        super(accessModifier, null);
        setReturnType(returnType);
    }
}
