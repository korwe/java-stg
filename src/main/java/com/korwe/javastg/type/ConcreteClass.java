package com.korwe.javastg.type;

import com.korwe.javastg.exception.IncompatibleMethodException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConcreteClass extends Class{

    private List<ConcreteMethod> concreteMethods;

    @Override
    protected void init(){
        super.init();
        concreteMethods = new ArrayList<>();
    }

    public ConcreteClass(String name) {
        super(name);
    }

    public ConcreteClass(String packageName, String name) {
        super(packageName, name);
    }

    public ConcreteClass(String packageName, String name, Class superClass) {
        super(packageName, name, superClass);
    }

    public List<ConcreteMethod> getConcreteMethods() {
        return concreteMethods;
    }

    public void setConcreteMethods(List<ConcreteMethod> concreteMethods) {
        this.concreteMethods = concreteMethods;
    }

    public void addConcreteMethod(ConcreteMethod method){
        concreteMethods.add(method);
    }

    @Override
    public void addMethod(Method method) {
        if(method.isConcrete()){
            addConcreteMethod((ConcreteMethod)method);
        }
        else{
            throw new IncompatibleMethodException();
        }
    }

    @Override
    public List<? extends Method> getMethods() {
        return concreteMethods;
    }

    public AbstractClass getAbstractCopy(){
        AbstractClass abstractClass = new AbstractClass(getPackageName(), getName(),getSuperClass());
        abstractClass.setAccessModifier(getAccessModifier());
        for(ConcreteMethod concreteMethod : getConcreteMethods()){
            abstractClass.addMethod(concreteMethod.getCopy());
        }

        for(Attribute attribute : getAttributes()){
            abstractClass.addAttribute(new Attribute(attribute.getAccessModifier(), attribute.getType(), attribute.getName(), attribute.isStatic()));
        }

        return abstractClass;
    }

}
