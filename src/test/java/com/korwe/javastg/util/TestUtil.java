package com.korwe.javastg.util;

import com.korwe.javastg.type.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestUtil {
    public static ConcreteClass getBasicConcreteClass(){
        return new ConcreteClass("some.place", "SomeClass");
    }

    public static ConcreteClass getConcreteClassWithConstructor(){
        ConcreteClass concreteClass = new ConcreteClass("some.place", "ClassWithConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, concreteClass);
        constructorMethod.addParamater(new Parameter(PrimitiveType.Int, "intValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }

    public static ConcreteClass getConcreteClassWithMultiArgConstructor(){
        ConcreteClass concreteClass = new ConcreteClass("some.place", "MultiArgConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, concreteClass);
        constructorMethod.addParamater(new Parameter(PrimitiveType.Int, "intValue"));
        constructorMethod.addParamater(new Parameter(PrimitiveType.Long, "longValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }
}
