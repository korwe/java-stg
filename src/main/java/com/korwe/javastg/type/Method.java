package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Method {
    private String name;
    private AccessModifier accessModifier;
    private TypeDefinition returnType;
    private List<Attribute> parameters;
    private boolean isStatic;

    public Method(){
        init();
    }

    public Method(String name){
        this.name = name;
        init();
    }

    public Method(AccessModifier accessModifier, String name){
        this.accessModifier = accessModifier;
        this.name = name;
        init();
    }

    public Method(TypeDefinition returnType, String name){
        this.returnType = returnType;
        this.name = name;
        init();
    }

    public Method(AccessModifier accessModifier, TypeDefinition returnType, String name){
        this.accessModifier = accessModifier;
        this.returnType = returnType;
        this.name = name;
        init();
    }

    private void init(){
        parameters = new ArrayList<>();
    }

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

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void addParamater(Attribute parameter) {
        parameters.add(parameter);
    }

    public boolean isAbstract() {
        return this.getClass().equals(AbstractMethod.class);
    }
}
