package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Parameter extends IDDeclaration{
    private boolean isFinal;

    public Parameter(){
        super();
    }

    public Parameter(TypeDefinition type, String name) {
        super(type, name);
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
