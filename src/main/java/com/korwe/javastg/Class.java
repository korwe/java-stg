package com.korwe.javastg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Class extends ReferenceType{
    private AccessModifier accessModifier;
    private Class superClass;
    private List<Attribute> attributes;
    private List<Method> concreteMethods;
    private List<Method> abstractMethods;

    public Class(){
        concreteMethods = new ArrayList<>();
        abstractMethods = new ArrayList<>();
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

    public List<Method> getConcreteMethods() {
        return concreteMethods;
    }

    public void setConcreteMethods(List<Method> concreteMethods) {
        this.concreteMethods = concreteMethods;
    }

    public List<Method> getAbstractMethods() {
        return abstractMethods;
    }

    public void setAbstractMethods(List<Method> abstractMethods) {
        for(Iterator<Method> iterator=abstractMethods.iterator(); iterator.hasNext();){
            iterator.next().setAbstract(true);
        }
        this.abstractMethods = abstractMethods;
    }

    public void addMethod(Method method){
        if(method.isAbstract()){
            abstractMethods.add(method);
        }
        else{
            concreteMethods.add(method);
        }
    }

    public void addConcreteMethod(Method method){
        method.setAbstract(false);
        concreteMethods.add(method);
    }

    public void addAbstractMethod(Method method){
        method.setAbstract(true);
        abstractMethods.add(method);
    }

    public List<Method> getMethods(){
        List<Method> methods = new ArrayList<>();

        methods.addAll(concreteMethods);
        methods.addAll(abstractMethods);

        return methods;
    }
}
