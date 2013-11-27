package com.korwe.javastg.definition;

import com.korwe.javastg.definition.ConcreteClass;
import com.korwe.javastg.type.ClassType;
import com.korwe.javastg.type.ParameterizedType;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ParameterizedConcreteClass extends ParameterizedType implements ClassType {
    public ParameterizedConcreteClass() {
        super();
    }

    public ParameterizedConcreteClass(ConcreteClass concreteClass) {
        super(concreteClass);
    }

}
