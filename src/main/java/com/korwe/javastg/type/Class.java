package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Class extends ReferenceType{
    private AccessModifier accessModifier;
    private Class superClass;
    private List<Attribute> attributes;
    private List<Interface> interfaces;

    public Class(String name) {
        super(null, name);
        init();
    }

    public Class(String packageName, String name) {
        super(packageName, name);
        init();
    }

    public Class(String packageName, String name, Class superClass) {
        super(packageName, name);
        this.superClass = superClass;
        init();
    }

    protected void init() {
        attributes = new ArrayList<>();
        interfaces = new ArrayList<>();
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;

    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public Class getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(Interface interfaceDef){
        this.interfaces.add(interfaceDef);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public boolean isAbstract(){
        return this.getClass().isAssignableFrom(AbstractClass.class);
    }

    public boolean isConcrete(){
        return this.getClass().isAssignableFrom(ConcreteClass.class);
    }

    public abstract List<? extends Method> getMethods();

    public abstract void addMethod(Method method);
}
