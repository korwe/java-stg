package com.korwe.javastg.type;


import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AbstractClass extends ConcreteClass{

    private List<AbstractMethod> abstractMethods;

    @Override
    public void init(){
        super.init();
        abstractMethods = new ArrayList<>();
    }

    public AbstractClass(String name) {
        super(name);
    }

    public AbstractClass(String packageName, String name) {
        super(packageName, name);
    }

    public AbstractClass(String packageName, String name, Class superClass) {
        super(packageName, name, superClass);
    }

    @Override
    public void addMethod(Method method){
        if(method.isAbstract()){
            abstractMethods.add((AbstractMethod)method);
        }
        else {
            super.addMethod(method);
        }
    }

    public void addAbstractMethod(AbstractMethod method){
        abstractMethods.add(method);
    }

    @Override
    public List<Method> getMethods(){
        List<Method> methods = new ArrayList<>();

        methods.addAll(getConcreteMethods());
        methods.addAll(abstractMethods);

        return methods;
    }

    public List<AbstractMethod> getAbstractMethods() {
        return abstractMethods;
    }

    public void setAbstractMethods(List<AbstractMethod> abstractMethods) {
        this.abstractMethods = abstractMethods;
    }

    public ConcreteClass getConcreteCopy(){
        ConcreteClass concreteClass = new ConcreteClass(getPackageName(), getName(),getSuperClass());
        concreteClass.setAccessModifier(getAccessModifier());
        for(ConcreteMethod concreteMethod : getConcreteMethods()){
            concreteClass.addMethod(concreteMethod.getCopy());
        }

        for(Attribute attribute : getAttributes()){
            concreteClass.addAttribute(new Attribute(attribute.getAccessModifier(), attribute.getType(), attribute.getName(), attribute.isStatic()));
        }

        return concreteClass;
    }

}
