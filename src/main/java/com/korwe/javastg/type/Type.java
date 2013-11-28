package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public interface Type {
    public String getName();
    public boolean isCompatibleWith(TypeValue value);
    public boolean isCompatibleWith(Type type);
    public boolean hasLiteralSupport();
}
