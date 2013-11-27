package com.korwe.javastg.definition;

import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.type.IDDeclaration;
import com.korwe.javastg.type.Type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ClassAttribute extends IDDeclaration {
    private AccessModifier accessModifier;
    private boolean isStatic;
    private boolean isFinal;

    public ClassAttribute(){}

    public ClassAttribute(TypeDefinition type, String name){
        super(type, name);
    }

    public ClassAttribute(AccessModifier accessModifier, Type type, String name) {
        super(type, name);
        this.accessModifier = accessModifier;
    }

    public ClassAttribute(AccessModifier accessModifier, Type type, String name, boolean isStatic) {
        super(type, name);
        this.accessModifier = accessModifier;
        this.isStatic = isStatic;
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

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
