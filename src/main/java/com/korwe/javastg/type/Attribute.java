package com.korwe.javastg.type;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Attribute {
    private String name;
    private TypeDefinition type;
    private AccessModifier accessModifier;
    private boolean isStatic;

    public Attribute(){}

    public Attribute(TypeDefinition type, String name) {
        this.type = type;
        this.name = name;
    }

    public Attribute(AccessModifier accessModifier, TypeDefinition type, String name) {
        this.accessModifier = accessModifier;
        this.type = type;
        this.name = name;
    }

    public Attribute(AccessModifier accessModifier, TypeDefinition type, String name, boolean isStatic) {
        this.accessModifier = accessModifier;
        this.type = type;
        this.name = name;
        this.isStatic = isStatic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeDefinition getType() {
        return type;
    }

    public void setType(TypeDefinition type) {
        this.type = type;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        this.isStatic = aStatic;
    }

    public String getCapitalizedName() {
        return StringUtils.capitalize(name);
    }
}
