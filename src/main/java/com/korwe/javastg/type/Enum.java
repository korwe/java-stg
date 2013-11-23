package com.korwe.javastg.type;

import com.korwe.javastg.exception.IncompatibleMethodException;
import com.korwe.javastg.value.TypeDefinitionValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Enum extends Class{
    private List<TypeDefinitionValue> values;
    private List<ConcreteMethod> methods;

    public Enum(String name) {
        super(null, name);
        init();
    }

    public Enum(String packageName, String name) {
        super(packageName, name);
        init();
    }

    @Override
    protected void init(){
        setDefaultConstructor(null);
        values = new ArrayList<>();
        methods = new ArrayList<>();
    }

    public List<TypeDefinitionValue> getValues() {
        return values;
    }

    public void setValues(List<TypeDefinitionValue> values) {
        this.values = values;
    }

    public List<ConcreteMethod> getMethods() {
        return methods;
    }

    @Override
    public void addMethod(Method method) {
        if(method.isConcrete()){
            methods.add((ConcreteMethod)method);
        }
        else{
            throw new IncompatibleMethodException();
        }
    }

    public void setMethods(List<ConcreteMethod> methods) {
        this.methods = methods;
    }
}
