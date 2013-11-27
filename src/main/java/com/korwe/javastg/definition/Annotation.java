package com.korwe.javastg.definition;

import com.korwe.javastg.type.AnnotationType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Annotation extends Reference implements AnnotationType{
    private List<AnnotationAttribute> attributes;

    public Annotation(String packageName, String name) {
        super(packageName, name);
        attributes = new ArrayList<>();
    }

    public List<AnnotationAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AnnotationAttribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(AnnotationAttribute attribute){
        this.attributes.add(attribute);
    }

    public AnnotationInstance getInstance() {
        return new AnnotationInstance(this);
    }
}
