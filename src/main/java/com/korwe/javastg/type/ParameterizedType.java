package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ParameterizedType extends ReferenceType{ //Extends reference type to be of the TypeDefinition family
    private Generifiable generifiable;
    private List<ReferenceType> parameterTypes;

    public ParameterizedType() {
        super(null, null);
        init();
    }

    public ParameterizedType(GenerifiableType generifiableType) {
        super(null, null);
        this.generifiable = generifiableType;
        init();
    }

    protected void init(){
        this.parameterTypes = new ArrayList<>();
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

    public void addParameterType(ReferenceType parameterType){
        this.parameterTypes.add(parameterType);
    }
}
