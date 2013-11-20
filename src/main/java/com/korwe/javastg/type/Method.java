package com.korwe.javastg.type;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Method {
    private String name;
    private AccessModifier accessModifier;
    private TypeDefinition returnType;
    private String returnValue;
    private String body;
    private List<Attribute> parameters;
    private boolean isAbstract;
    private boolean isStatic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public TypeDefinition getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeDefinition returnType) {
        this.returnType = returnType;
    }

    public List<Attribute> getParameters() {
        return parameters;
    }

    public void setParameters(List<Attribute> parameters) {
        this.parameters = parameters;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
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
}
