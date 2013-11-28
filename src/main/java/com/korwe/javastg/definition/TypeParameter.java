package com.korwe.javastg.definition;

import com.korwe.javastg.type.Type;
import com.korwe.javastg.value.TypeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TypeParameter implements Type {
    private List<Reference> parentTypes;
    private String name;

    public TypeParameter(){
        this.parentTypes = new ArrayList<>();
    }

    public TypeParameter(String name){
        this.parentTypes = new ArrayList<>();
        setName(name);
    }

    public TypeParameter(String name, List<Reference> parentTypes) {
        setName(name);
        this.parentTypes = parentTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    @Override
    public boolean isCompatibleWith(TypeValue value) {
        return value == null || isCompatibleWith(value.getType());
    }

    @Override
    public boolean isCompatibleWith(Type type) {
        //type should be compatible with all parentTypes
        for (Reference reference : parentTypes) {
            if(!reference.isCompatibleWith(type)) return false;
        }
        return true;
    }

    @Override
    public boolean hasLiteralSupport() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
