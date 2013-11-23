package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TypeParameter{
    private List<TypeDefinition> parentTypes;
    private List<TypeParameterName> names;

    public TypeParameter(){
        this.names = new ArrayList<>();
        this.parentTypes = new ArrayList<>();
    }

    public TypeParameter(List<String> names){
        this.parentTypes = new ArrayList<>();
        this.names = new ArrayList<>();
        for(String name : names){
            addTypeName(name);
        }
    }

    public TypeParameter(List<String> names, List<TypeDefinition> parentTypes) {
        this.names = new ArrayList<>();
        for(String name : names){
            addTypeName(name);
        }
        this.parentTypes = parentTypes;
    }

    public List<TypeParameterName> getNames() {
        return names;
    }

    public void setNames(List<TypeParameterName> names) {
        this.names = names;
    }

    public List<TypeDefinition> getParentTypes() {
        return parentTypes;
    }

    public void setParentTypes(List<TypeDefinition> parentTypes) {
        this.parentTypes = parentTypes;
    }

    public void addTypeName(String name){
        if(getTypeForName(name)==null){
            this.names.add(new TypeParameterName(name));
        }
    }

    public void addTypeName(TypeParameterName name){
        this.names.add(name);
    }

    public TypeParameterName getTypeForName(String name){
        for(TypeParameterName tpn : names){
            if(tpn.getName().equals(name)) return tpn;
        }
        return null;
    }

}
