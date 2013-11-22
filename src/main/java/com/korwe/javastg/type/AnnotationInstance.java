package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeDefinitionValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AnnotationInstance {
    private Annotation annotation;
    private Map<String, TypeDefinitionValue> values = new HashMap<>();

    public AnnotationInstance(Annotation annotation){
        this.annotation = annotation;
    }

    public TypeDefinitionValue setValue(String name, TypeDefinitionValue value){
        return values.put(name, value);
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Map<String, TypeDefinitionValue> getValues() {
        return values;
    }
}
