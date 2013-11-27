package com.korwe.javastg.type;

import com.korwe.javastg.definition.TypeParameter;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public interface GenerifiableType {
    public void addTypeParameter(TypeParameter typeParameter);
    public List<TypeParameter> getTypeParameters();
    public void setTypeParameters(List<TypeParameter> typeParameters);
}
