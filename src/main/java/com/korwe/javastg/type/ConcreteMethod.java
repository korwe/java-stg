package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConcreteMethod extends Method{
    private String returnValue;
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

    public ConcreteMethod(AccessModifier accessModifier, TypeDefinition returnType, String name) {
        super(accessModifier, returnType, name);
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public AbstractMethod getAbstractCopy(){
        AbstractMethod abstractMethod = new AbstractMethod(this.getAccessModifier(), this.getReturnType(), this.getName());
        for(Attribute paramater : this.getParameters()){
            abstractMethod.addParamater(new Attribute(paramater.getType(), paramater.getName()));
        }
        return abstractMethod;
    }
}
