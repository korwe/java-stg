package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Class extends ReferenceType{
    private AccessModifier accessModifier;
    private Class superClass;
    private List<Attribute> attributes;
    private List<ConcreteMethod> concreteMethods;
    private List<AbstractMethod> abstractMethods;
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

    private void init() {
        attributes = new ArrayList<>();
        concreteMethods = new ArrayList<>();
        abstractMethods = new ArrayList<>();
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

    public List<ConcreteMethod> getConcreteMethods() {
        return concreteMethods;
    }

    public void setConcreteMethods(List<ConcreteMethod> concreteMethods) {
        this.concreteMethods = concreteMethods;
    }

    public List<AbstractMethod> getAbstractMethods() {
        return abstractMethods;
    }

    public void setAbstractMethods(List<AbstractMethod> abstractMethods) {
        this.abstractMethods = abstractMethods;
    }

    public void addMethod(Method method){
        if(method.isAbstract()){
            abstractMethods.add((AbstractMethod)method);
        }
        else{
            concreteMethods.add((ConcreteMethod)method);
        }
    }

    public void addConcreteMethod(ConcreteMethod method){
        concreteMethods.add(method);
    }

    public void addAbstractMethod(AbstractMethod method){
        abstractMethods.add(method);
    }

    public List<Method> getMethods(){
        List<Method> methods = new ArrayList<>();

        methods.addAll(concreteMethods);
        methods.addAll(abstractMethods);

        return methods;
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
}
