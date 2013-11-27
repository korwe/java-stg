package com.korwe.javastg.definition;

import com.korwe.javastg.definition.AbstractClass;
import com.korwe.javastg.type.ClassType;
import com.korwe.javastg.type.ParameterizedType;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ParameterizedAbstractClass extends ParameterizedType implements ClassType {
    public ParameterizedAbstractClass() {
    }

    public ParameterizedAbstractClass(AbstractClass abstractClass) {
        super(abstractClass);
    }
}
