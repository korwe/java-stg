package com.korwe.javastg.type;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class GenerifiedType extends TypeDefinition{
    private Generifiable generifiable;
    private List<ReferenceType> parameterTypes;

    public GenerifiedType() {
        super(null);
    }

    public GenerifiedType(GenerifiableType generifiableType) {
        super(null);
        this.generifiable = generifiableType;
    }

    public Generifiable getGenerifiable() {
        return generifiable;
    }

    public void setGenerifiable(GenerifiableType generifiable) {
        this.generifiable = generifiable;
    }

    public List<ReferenceType> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<ReferenceType> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
