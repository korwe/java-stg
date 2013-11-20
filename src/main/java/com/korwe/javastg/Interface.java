package com.korwe.javastg;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Interface extends ReferenceType{
    private List<Method> methods;
    private Interface superInterface;

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public Interface getSuperInterface() {
        return superInterface;
    }

    public void setSuperInterface(Interface superInterface) {
        this.superInterface = superInterface;
    }
}
