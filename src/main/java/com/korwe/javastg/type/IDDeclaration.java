package com.korwe.javastg.type;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class IDDeclaration extends Annotatable{
    private TypeDefinition type;
    private String name;

    public IDDeclaration(){

    }

    public IDDeclaration(TypeDefinition type, String name) {
        setType(type);
        setName(name);
    }

    public TypeDefinition getType() {
        return type;
    }

    public void setType(TypeDefinition type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitalizedName() {
        return StringUtils.capitalize(name);
    }
}
