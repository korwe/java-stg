package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Enum extends ReferenceType{
    private List<String> values;
    private List<Attribute> attributes;
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

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<ConcreteMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<ConcreteMethod> methods) {
        this.methods = methods;
    }
}
