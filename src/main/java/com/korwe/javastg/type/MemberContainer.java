package com.korwe.javastg.type;

import com.korwe.javastg.definition.*;
import com.korwe.javastg.exception.NoConstructorFoundException;
import com.korwe.javastg.value.TypeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class MemberContainer extends Generifiable {
    private AccessModifier accessModifier;
    private List<ClassAttribute> attributes;
    private List<InterfaceType> interfaces;
    private List<ConstructorMethod> constructors = new ArrayList<>();
    private ConstructorMethod defaultConstructor = new ConstructorMethod(AccessModifier.Public, this);

    public MemberContainer(String name) {
        super(null, name);
        init();
    }

    public MemberContainer(String packageName, String name) {
        super(packageName, name);
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

    public List<ClassAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ClassAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<InterfaceType> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<InterfaceType> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(InterfaceType interfaceDef){
        this.interfaces.add(interfaceDef);
    }

    public void addAttribute(ClassAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void addConstructor(ConstructorMethod constructorMethod){
        this.constructors.add(constructorMethod);
    }

    public List<ConstructorMethod> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<ConstructorMethod> constructors) {
        this.constructors = constructors;
    }

    public boolean isAbstract(){
        return this.getClass().isAssignableFrom(AbstractClass.class);
    }

    public boolean isConcrete(){
        return this.getClass().isAssignableFrom(ConcreteClass.class);
    }

    public ConstructorMethod constructorForArguments(TypeValue[] constructorArguments){
        ConstructorMethod constructorMethod = null;
        for(ConstructorMethod constructor : constructors){
            if(constructor.supportsArguments(constructorArguments)){
                if(constructorMethod == null || constructor.getParameters().size() > constructorMethod.getParameters().size()){
                    constructorMethod = constructor;
                }
            }
        }

        if(constructorMethod == null){
            throw new NoConstructorFoundException();
        }

        return constructorMethod;
    }

    public ConstructorMethod constructorForArguments(Map<String, TypeValue> constructorArguments){
        ConstructorMethod constructorMethod = null;
        for(ConstructorMethod constructor : constructors){
            if(constructor.supportsArguments(constructorArguments)){
                if(constructorMethod == null || constructor.getParameters().size() > constructorMethod.getParameters().size()){
                    constructorMethod = constructor;
                }
            }
        }

        if(constructorMethod == null){
            throw new NoConstructorFoundException();
        }

        return constructorMethod;

    }

    public abstract List<? extends Method> getMethods();

    public abstract void addMethod(Method method);

    public void setDefaultConstructor(ConstructorMethod constructor){
        this.defaultConstructor = constructor;
    }

    public ConstructorMethod getDefaultConstructor() {
        if(constructors.size() == 0){
            return defaultConstructor;
        }

        for(ConstructorMethod constructor : constructors){
            if(constructor.getParameters().size() == 0){
                return constructor;
            }
        }

        return null;
    }
}
