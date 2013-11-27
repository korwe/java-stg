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
}
