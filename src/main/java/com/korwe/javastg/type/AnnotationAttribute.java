package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeDefinitionValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AnnotationAttribute extends Attribute {

    private TypeDefinitionValue defaultValue;
    public AnnotationAttribute(){}

    public AnnotationAttribute(TypeDefinition type, String name) {
        super(type, name);
    }

    public AnnotationAttribute(TypeDefinition type, String name, TypeDefinitionValue defaultValue){
        super(type, name);
        this.defaultValue = defaultValue;
    }

    public TypeDefinitionValue getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(TypeDefinitionValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
