package com.korwe.javastg.type;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public interface Generifiable {
    public void addTypeParameter(TypeParameter typeParameter);
    public List<TypeParameter> getTypeParameters();
    public void setTypeParameters(List<TypeParameter> typeParameters);
}
