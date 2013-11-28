package com.korwe.javastg.definition;

import com.korwe.javastg.type.Method;
import com.korwe.javastg.type.Type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AbstractMethod extends Method {

    public AbstractMethod() {
    }

    public AbstractMethod(String name) {
        super(name);
    }

    public AbstractMethod(AccessModifier accessModifier, String name) {
        super(accessModifier, name);
    }

    public AbstractMethod(Type returnType, String name) {
        super(returnType, name);
    }

    public AbstractMethod(AccessModifier accessModifier, Type returnType, String name) {
        super(accessModifier, returnType, name);
    }

    public AbstractMethod(AccessModifier accessModifier, Type returnType, String name, boolean isStatic) {
        super(accessModifier, returnType, name, isStatic);
    }

    public ConcreteMethod getConcreteCopy(){
        ConcreteMethod concreteMethod = new ConcreteMethod(this.getAccessModifier(), this.getReturnType(), this.getName(), this.isStatic());
        for(Parameter paramater : this.getParameters()){
            concreteMethod.addParamater(new Parameter(paramater.getType(), paramater.getName()));
        }

        for(TypeParameter typeParameter: getTypeParameters()){
            concreteMethod.addTypeParameter(typeParameter);
        }

        return concreteMethod;
    }

}
