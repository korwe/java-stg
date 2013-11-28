package com.korwe.javastg.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TypeParameter{
    private List<Reference> parentTypes;
    private TypeParameterName name;

    public TypeParameter(){
        this.parentTypes = new ArrayList<>();
    }

    public TypeParameter(String name){
        this.parentTypes = new ArrayList<>();
        setName(new TypeParameterName(name));
    }

    public TypeParameter(String name, List<Reference> parentTypes) {
        setName(new TypeParameterName(name));
        this.parentTypes = parentTypes;
    }

    public TypeParameterName getName() {
        return name;
    }

    public void setName(TypeParameterName name) {
        this.name = name;
    }

    public List<Reference> getParentTypes() {
        return parentTypes;
    }

    public void setParentTypes(List<Reference> parentTypes) {
        this.parentTypes = parentTypes;
    }

    public void addParentType(Reference parentType){
        this.parentTypes.add(parentType);
    }

    public TypeParameter getCopy(){
        TypeParameter copy = new TypeParameter();
        copy.setName(getName());

        for(Reference parentType : getParentTypes()){
            copy.addParentType(parentType);
        }

        return copy;
    }

}
