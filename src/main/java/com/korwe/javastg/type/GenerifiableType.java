package com.korwe.javastg.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class GenerifiableType extends ReferenceType implements Generifiable{
    private List<TypeParameter> typeParameters;

    public GenerifiableType(String packageName, String name) {
        super(packageName, name);
        this.typeParameters = new ArrayList<>();
    }

    @Override
    public void addTypeParameter(TypeParameter typeParam){
        this.typeParameters.add(typeParam);
    }

    @Override
    public List<TypeParameter> getTypeParameters() {
        return typeParameters;
    }

    @Override
    public void setTypeParameters(List<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
    }
}
