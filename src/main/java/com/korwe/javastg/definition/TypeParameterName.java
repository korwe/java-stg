package com.korwe.javastg.definition;

import com.korwe.javastg.type.Type;
import com.korwe.javastg.value.TypeValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TypeParameterName implements Type {
    private String name;

    public TypeParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isCompatibleWith(TypeValue value) {
        return false;
    }

    @Override
    public boolean hasLiteralSupport() {
        return false;
    }
}
