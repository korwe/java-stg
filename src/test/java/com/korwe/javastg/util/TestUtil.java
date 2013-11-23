package com.korwe.javastg.util;

import com.korwe.javastg.type.ConcreteClass;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestUtil {
    public static ConcreteClass getBasicConcreteClass(){
        return new ConcreteClass("some.place", "SomeClass");
    }
}
