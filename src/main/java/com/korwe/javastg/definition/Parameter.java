package com.korwe.javastg.definition;

import com.korwe.javastg.type.IDDeclaration;
import com.korwe.javastg.type.Type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Parameter extends IDDeclaration {
    private boolean isFinal;

    public Parameter(){
        super();
    }

    public Parameter(Type type, String name) {
        super(type, name);
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;

        if(!super.equals(o)) return false;
        Parameter parameter = (Parameter) o;

        if (isFinal != parameter.isFinal) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (isFinal ? 1 : 0);
    }
}
