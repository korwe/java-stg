package com.korwe.javastg.type;

import com.korwe.javastg.definition.AnnotationInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Annotatable {
    private List<AnnotationInstance> annotations = new ArrayList<>();

    public void addAnnotation(AnnotationInstance annotation){
        annotations.add(annotation);
    }

    public void removeAnnotation(AnnotationInstance annotation){
        annotations.remove(annotation);
    }

    public List<AnnotationInstance> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationInstance> annotations) {
        this.annotations = annotations;
    }
}
