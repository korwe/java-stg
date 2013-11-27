package com.korwe.javastg.definition;

import com.korwe.javastg.type.IDDeclaration;
import com.korwe.javastg.type.Type;
import com.korwe.javastg.value.TypeValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AnnotationAttribute extends IDDeclaration {

    private TypeValue defaultValue;
    public AnnotationAttribute(){}

    public AnnotationAttribute(Type type, String name) {
        super(type, name);
    }

    public AnnotationAttribute(Type type, String name, TypeValue defaultValue){
        super(type, name);
        this.defaultValue = defaultValue;
    }

    public TypeValue getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(TypeValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
