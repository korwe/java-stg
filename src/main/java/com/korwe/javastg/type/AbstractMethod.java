package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AbstractMethod extends Method{

    public AbstractMethod() {
    }

    public AbstractMethod(String name) {
        super(name);
    }

    public AbstractMethod(AccessModifier accessModifier, String name) {
        super(accessModifier, name);
    }

    public AbstractMethod(TypeDefinition returnType, String name) {
        super(returnType, name);
    }

    public AbstractMethod(AccessModifier accessModifier, TypeDefinition returnType, String name) {
        super(accessModifier, returnType, name);
    }

    public AbstractMethod(AccessModifier accessModifier, TypeDefinition returnType, String name, boolean isStatic) {
        super(accessModifier, returnType, name, isStatic);
    }

    public ConcreteMethod getConcreteCopy(){
        ConcreteMethod concreteMethod = new ConcreteMethod(this.getAccessModifier(), this.getReturnType(), this.getName());
        for(Attribute paramater : this.getParameters()){
            concreteMethod.addParamater(new Attribute(paramater.getType(), paramater.getName()));
        }
        return concreteMethod;
    }

}
