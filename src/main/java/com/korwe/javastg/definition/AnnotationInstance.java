package com.korwe.javastg.definition;

import com.korwe.javastg.definition.Annotation;
import com.korwe.javastg.value.TypeValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class AnnotationInstance {
    private Annotation annotation;
    private Map<String, TypeValue> values = new HashMap<>();

    public AnnotationInstance(Annotation annotation){
        this.annotation = annotation;
    }

    public TypeValue setValue(String name, TypeValue value){
        return values.put(name, value);
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Map<String, TypeValue> getValues() {
        return values;
    }
}
