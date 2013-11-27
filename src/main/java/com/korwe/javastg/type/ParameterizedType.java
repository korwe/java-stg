package com.korwe.javastg.type;

import com.korwe.javastg.definition.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class ParameterizedType extends Reference {
    private GenerifiableType generifiable;
    private List<Reference> parameterTypes;

    public ParameterizedType() {
        super(null, null); //Just a place holder type for generifiable - no package or name
        init();
    }

    public ParameterizedType(com.korwe.javastg.definition.Generifiable generifiableType) {
        super(null, null); //Just a place holder type for generifiable - no package or name
        this.generifiable = generifiableType;
        init();
    }

    protected void init(){
        this.parameterTypes = new ArrayList<>();
    }

    public GenerifiableType getGenerifiable() {
        return generifiable;
    }

    protected void setGenerifiable(com.korwe.javastg.definition.Generifiable generifiable) {
        this.generifiable = generifiable;
    }

    public List<Reference> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<Reference> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public void addParameterType(Reference parameterType){
        this.parameterTypes.add(parameterType);
    }
}
