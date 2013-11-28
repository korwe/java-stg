package com.korwe.javastg.definition;

import com.korwe.javastg.type.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConcreteMethod extends Method {
    private String body;

    public ConcreteMethod() {
    }

    public ConcreteMethod(String name) {
        super(name);
    }

    public ConcreteMethod(AccessModifier accessModifier, String name) {
        super(accessModifier, name);
    }

    public ConcreteMethod(TypeDefinition returnType, String name) {
        super(returnType, name);
    }

    public ConcreteMethod(AccessModifier accessModifier, Type returnType, String name) {
        super(accessModifier, returnType, name);
    }

    public ConcreteMethod(AccessModifier accessModifier, Type returnType, String name, boolean isStatic) {
        super(accessModifier, returnType, name, isStatic);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public AbstractMethod getAbstractCopy(){
        AbstractMethod abstractMethod = new AbstractMethod();
        populatedWithMembers(abstractMethod);
        return abstractMethod;
    }

    public ConcreteMethod getCopy() {
        ConcreteMethod newMethod = new ConcreteMethod(getAccessModifier(), getReturnType(), getName(), isStatic());
        populatedWithMembers(newMethod);
        return newMethod;
    }

    private void populatedWithMembers(Method method) {
        method.setName(getName());
        method.setAccessModifier(getAccessModifier());
        method.setReturnType(getReturnType());
        method.setStatic(isStatic());

        for(Parameter paramater : this.getParameters()){
            method.addParamater(new Parameter(paramater.getType(), paramater.getName()));
        }

        for(TypeParameter typeParameter : getTypeParameters()){
            method.addTypeParameter(typeParameter);
        }
    }
}
