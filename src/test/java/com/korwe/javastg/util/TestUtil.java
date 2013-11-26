package com.korwe.javastg.util;

import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;
import com.korwe.javastg.type.Enum;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestUtil {
    public static ConcreteClass getBasicConcreteClass(){
        return new ConcreteClass("classes.place", "SomeClass");
    }

    public static ConcreteClass getBasicExtendedClass(){
        return new ConcreteClass("classes.place", "BasicExtendedClass", getBasicConcreteClass());
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

    public static ConcreteClass getBasicTypeParameterParentType(){
        return new ConcreteClass("typeParameter.place", "Type1");
    }

    public static ConcreteClass getBasicExtendedTypeParameterParentType(){
        return new ConcreteClass("typeParameter.place", "ExtendedType1", getBasicTypeParameterParentType());
    }

    public static TypeParameter getBasicTypeParameterWithParent(){
        TypeParameter typeParameter = new TypeParameter();
        typeParameter.addTypeName("T");
        typeParameter.addParentType(getBasicTypeParameterParentType());
        return typeParameter;
    }

    public static TypeParameter getMultiBoundTypeParameter(){
        TypeParameter typeParameter = new TypeParameter();
        typeParameter.addTypeName("X");
        typeParameter.addParentType(new ConcreteClass("typeParameter.place", "Type2"));
        typeParameter.addParentType(new ConcreteClass("typeParameter.place", "Type3"));
        return typeParameter;
    }

    public static GenerifiableType getGenerifiableType(){
        GenerifiableType generifiableType = new ConcreteClass("generifiable.place", "GenerifiableOne");
        generifiableType.addTypeParameter(getBasicTypeParameterWithParent());
        return generifiableType;
    }

    public static GenerifiableType getMultiBoundGenerifiableType(){
        GenerifiableType generifiableType = new ConcreteClass("generifiable.place", "MultiBoundGenerifiable");
        generifiableType.addTypeParameter(getMultiBoundTypeParameter());
        return  generifiableType;
    }

    public static ParameterizedType getBasicParameterizedType(){
        ParameterizedType parameterizedType = new ParameterizedType(getGenerifiableType());
        parameterizedType.addParameterType(getBasicExtendedTypeParameterParentType());
        return parameterizedType;
    }

    public static ParameterizedType getMultiBoundParameterizedType(){
        ParameterizedType parameterizedType = new ParameterizedType(getMultiBoundGenerifiableType());
        TypeParameter typeParameter = parameterizedType.getGenerifiable().getTypeParameters().get(0);
        parameterizedType.addParameterType(new ConcreteClass("typeParameter.place", "ExtendedType2", (ConcreteClass)typeParameter.getParentTypes().get(0)));
        parameterizedType.addParameterType(new ConcreteClass("typeParameter.place", "ExtendedType3", (ConcreteClass) typeParameter.getParentTypes().get(1)));
        return parameterizedType;
    }

    public static ConcreteMethod getPrivateAnnotatedParameterizedConcreteMethodWithParameterizedParameter(){
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Private, new ConcreteClass("method.classes","MethodReturnType"), "testPrivateMethod");
        method.addParamater(new Parameter(new ConcreteClass("method.classes", "MethodParam1"), "param1"));

        TypeParameter parameterTypeParameter = new TypeParameter();
        parameterTypeParameter.addTypeName("S");
        ConcreteClass methodParameterType1 = new ConcreteClass("method.classes", "MethodParameterType1");
        parameterTypeParameter.addParentType(methodParameterType1);
        ConcreteClass parameterizedClass = new ConcreteClass("method.classes", "MethodParameterizedClass");
        parameterizedClass.addTypeParameter(parameterTypeParameter);
        ParameterizedType parameterizedType = new ParameterizedType(parameterizedClass);
        parameterizedType.addParameterType(new ConcreteClass("method.classes", "ExtendedMethodParameterType1", methodParameterType1));


        TypeParameter parameterType = new TypeParameter();
        parameterType.addTypeName("X");
        parameterType.addParentType(new ConcreteClass("method.classes", "MethodParameterType2"));
        method.addParamater(new Parameter(parameterizedType, "param2"));
        method.addTypeParameter(parameterType);

        method.addAnnotation(new AnnotationInstance(new Annotation("method.annotations", "MethodAnnotation1")));


        return method;
    }

    public static AbstractMethod getPrivateAnnotatedParameterizedAbstractMethodWithParameterizedParameter(){
        AbstractMethod method = new AbstractMethod(AccessModifier.Private, new ConcreteClass("method.classes","MethodReturnType2"), "testPublicMethod");
        method.addParamater(new Parameter(new ConcreteClass("method.classes", "AbstractMethodParam1"), "param1"));

        TypeParameter parameterTypeParameter = new TypeParameter();
        parameterTypeParameter.addTypeName("S");
        ConcreteClass methodParameterType1 = new ConcreteClass("method.classes", "AbstractMethodParameterType1");
        parameterTypeParameter.addParentType(methodParameterType1);
        ConcreteClass parameterizedClass = new ConcreteClass("method.classes", "AbstractMethodParameterizedClass");
        parameterizedClass.addTypeParameter(parameterTypeParameter);
        ParameterizedType parameterizedType = new ParameterizedType(parameterizedClass);
        parameterizedType.addParameterType(new ConcreteClass("method.classes", "ExtendedAbstractMethodParameterType1", methodParameterType1));


        TypeParameter parameterType = new TypeParameter();
        parameterType.addTypeName("X");
        parameterType.addParentType(new ConcreteClass("method.classes", "AbstractMethodParameterType2"));
        method.addParamater(new Parameter(parameterizedType, "param2"));
        method.addTypeParameter(parameterType);

        method.addAnnotation(new AnnotationInstance(new Annotation("method.annotations", "AbstractMethodAnnotation1")));


        return method;
    }
}
