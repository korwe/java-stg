package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeDefinitionValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Enum extends ReferenceType{
    private List<TypeDefinitionValue> values;
    private List<ClassAttribute> attributes;
    private List<ConcreteMethod> methods;

    public Enum(String name) {
        super(null, name);
        init();
    }

    public Enum(String packageName, String name) {
        super(packageName, name);
        init();
    }

    private void init(){
        values = new ArrayList<>();
        attributes = new ArrayList<>();
        methods = new ArrayList<>();
    }

    public List<TypeDefinitionValue> getValues() {
        return values;
    }

    public void setValues(List<TypeDefinitionValue> values) {
        this.values = values;
    }

    public List<ClassAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ClassAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ConcreteMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<ConcreteMethod> methods) {
        this.methods = methods;
    }
}
