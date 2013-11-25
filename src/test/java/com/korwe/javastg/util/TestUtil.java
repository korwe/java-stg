package com.korwe.javastg.util;

import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Enum;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestUtil {
    public static ConcreteClass getBasicConcreteClass(){
        return new ConcreteClass("classes.place", "SomeClass");
    }

    public static ConcreteClass getConcreteClassWithConstructor(){
        ConcreteClass concreteClass = new ConcreteClass("classes.place", "ClassWithConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, concreteClass);
        constructorMethod.addParamater(new Parameter(PrimitiveType.Int, "intValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }

    public static ConcreteClass getConcreteClassWithMultiArgConstructor(){
        ConcreteClass concreteClass = new ConcreteClass("classes.place", "ClassWithMultiArgConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, concreteClass);
        constructorMethod.addParamater(new Parameter(PrimitiveType.Int, "intValue"));
        constructorMethod.addParamater(new Parameter(PrimitiveType.Long, "longValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }

    public static Enum getBasicEnum(){
        return new Enum("enumeration.place", "SomeEnum");
    }

    public static Enum getEnumWithConstructor(){
        Enum enumDef = new Enum("enumeration.place", "EnumWithConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, enumDef);
        constructorMethod.addParamater(new Parameter(BoxableType.String, "stringValue"));
        enumDef.addConstructor(constructorMethod);
        return enumDef;
    }

    public static Enum getEnumWithMultiArgConstructor(){
        Enum enumDef = new Enum("enumeration.place", "EnumWithMultiArgConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, enumDef);
        constructorMethod.addParamater(new Parameter(PrimitiveType.Char, "charValue"));
        constructorMethod.addParamater(new Parameter(PrimitiveType.Double, "doubleValue"));
        enumDef.addConstructor(constructorMethod);
        return enumDef;
    }

    public static Annotation getBasicAnnotation(){
        return new Annotation("annotations.place", "BasicAnnotation");
    }
}
