package com.korwe.javastg;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Method {
    private String name;
    private AccessModifier accessModifier;
    private String returnType;
    private List<Attribute> parameters;

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

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<Attribute> getParameters() {
        return parameters;
    }

    public void setParameters(List<Attribute> parameters) {
        this.parameters = parameters;
    }
}
