package com.korwe.javastg.util;

import com.korwe.javastg.definition.*;
import com.korwe.javastg.definition.Enum;
import com.korwe.javastg.definition.Generifiable;
import com.korwe.javastg.definition.AnnotationInstance;
import com.korwe.javastg.definition.TypeParameter;
import com.korwe.javastg.definition.ParameterizedConcreteClass;
import com.korwe.javastg.definition.ParameterizedInterface;
import com.korwe.javastg.type.*;
import com.korwe.javastg.value.TypeValue;
import org.stringtemplate.v4.ST;

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
        constructorMethod.addParamater(new Parameter(Primitive.Int, "intValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }

    public static ConcreteClass getConcreteClassWithMultiArgConstructor(){
        ConcreteClass concreteClass = new ConcreteClass("classes.place", "ClassWithMultiArgConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, concreteClass);
        constructorMethod.addParamater(new Parameter(Primitive.Int, "intValue"));
        constructorMethod.addParamater(new Parameter(Primitive.Long, "longValue"));
        concreteClass.addConstructor(constructorMethod);
        return concreteClass;
    }

    public static Enum getBasicEnum(){
        return new Enum("enumeration.place", "SomeEnum");
    }

    public static Enum getEnumWithConstructor(){
        Enum enumDef = new Enum("enumeration.place", "EnumWithConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, enumDef);
        constructorMethod.addParamater(new Parameter(Boxable.String, "stringValue"));
        enumDef.addConstructor(constructorMethod);
        return enumDef;
    }

    public static Enum getEnumWithMultiArgConstructor(){
        Enum enumDef = new Enum("enumeration.place", "EnumWithMultiArgConstructor");
        ConstructorMethod constructorMethod = new ConstructorMethod(AccessModifier.Public, enumDef);
        constructorMethod.addParamater(new Parameter(Primitive.Char, "charValue"));
        constructorMethod.addParamater(new Parameter(Primitive.Double, "doubleValue"));
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
        TypeParameter typeParameter = new TypeParameter("T");
        typeParameter.addParentType(getBasicTypeParameterParentType());
        return typeParameter;
    }

    public static TypeParameter getMultiBoundTypeParameter(){
        TypeParameter typeParameter = new TypeParameter("X");
        typeParameter.addParentType(new ConcreteClass("typeParameter.place", "Type2"));
        typeParameter.addParentType(new ConcreteClass("typeParameter.place", "Type3"));
        return typeParameter;
    }

    public static Generifiable getGenerifiableType(){
        Generifiable generifiableType = new ConcreteClass("generifiable.place", "GenerifiableOne");
        generifiableType.addTypeParameter(getBasicTypeParameterWithParent());
        return generifiableType;
    }

    public static Generifiable getMultiBoundGenerifiableType(){
        Generifiable generifiableType = new ConcreteClass("generifiable.place", "MultiBoundGenerifiable");
        generifiableType.addTypeParameter(getMultiBoundTypeParameter());
        return  generifiableType;
    }

    public static ParameterizedType getBasicParameterizedType(){
        ParameterizedType parameterizedType = new ParameterizedType(getGenerifiableType()){};
        parameterizedType.addParameterType(getBasicExtendedTypeParameterParentType());
        return parameterizedType;
    }

    public static ParameterizedType getMultiBoundParameterizedType(){
        ParameterizedType parameterizedType = new ParameterizedType(getMultiBoundGenerifiableType()){};
        TypeParameter typeParameter = parameterizedType.getGenerifiable().getTypeParameters().get(0);
        parameterizedType.addParameterType(new ConcreteClass("typeParameter.place", "ExtendedType2", (ConcreteClass)typeParameter.getParentTypes().get(0)));
        parameterizedType.addParameterType(new ConcreteClass("typeParameter.place", "ExtendedType3", (ConcreteClass) typeParameter.getParentTypes().get(1)));
        return parameterizedType;
    }

    public static ConcreteMethod getPrivateAnnotatedParameterizedConcreteMethodWithParameterizedParameter(){
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Private, new ConcreteClass("method.classes","MethodReturnType"), "testPrivateMethod");
        method.addParamater(new Parameter(new ConcreteClass("method.classes", "MethodParam1"), "param1"));

        TypeParameter parameterTypeParameter = new TypeParameter("S");
        ConcreteClass methodParameterType1 = new ConcreteClass("method.classes", "MethodParameterType1");
        parameterTypeParameter.addParentType(methodParameterType1);
        ConcreteClass parameterizedClass = new ConcreteClass("method.classes", "MethodParameterizedClass");
        parameterizedClass.addTypeParameter(parameterTypeParameter);
        ParameterizedType parameterizedType = new ParameterizedType(parameterizedClass){};
        parameterizedType.addParameterType(new ConcreteClass("method.classes", "ExtendedMethodParameterType1", methodParameterType1));


        TypeParameter parameterType = new TypeParameter("X");
        parameterType.addParentType(new ConcreteClass("method.classes", "MethodParameterType2"));
        method.addParamater(new Parameter(parameterizedType, "param2"));
        method.addTypeParameter(parameterType);

        method.addAnnotation(new AnnotationInstance(new Annotation("method.annotations", "MethodAnnotation1")));


        return method;
    }

    public static AbstractMethod getPrivateAnnotatedParameterizedAbstractMethodWithParameterizedParameter(){
        AbstractMethod method = new AbstractMethod(AccessModifier.Private, new ConcreteClass("method.classes","MethodReturnType2"), "testPublicMethod");
        method.addParamater(new Parameter(new ConcreteClass("method.classes", "AbstractMethodParam1"), "param1"));

        TypeParameter parameterTypeParameter = new TypeParameter("S");
        ConcreteClass methodParameterType1 = new ConcreteClass("method.classes", "AbstractMethodParameterType1");
        parameterTypeParameter.addParentType(methodParameterType1);
        ConcreteClass parameterizedClass = new ConcreteClass("method.classes", "AbstractMethodParameterizedClass");
        parameterizedClass.addTypeParameter(parameterTypeParameter);
        ParameterizedType parameterizedType = new ParameterizedType(parameterizedClass){};
        parameterizedType.addParameterType(new ConcreteClass("method.classes", "ExtendedAbstractMethodParameterType1", methodParameterType1));


        TypeParameter parameterType = new TypeParameter("X");
        parameterType.addParentType(new ConcreteClass("method.classes", "AbstractMethodParameterType2"));
        method.addParamater(new Parameter(parameterizedType, "param2"));
        method.addTypeParameter(parameterType);

        method.addAnnotation(new AnnotationInstance(new Annotation("method.annotations", "AbstractMethodAnnotation1")));


        return method;
    }

    //Annotated parameterized extended class with methods, constructors, attributes and interfaces
    public static ConcreteClass getAPECClass_CMAI(){
        ConcreteClass concreteClass = new ConcreteClass("classes.place", "APECClass_CMAI");

        //TYPE PARAMETERS
        TypeParameter classTypeParameter = new TypeParameter("X");
        classTypeParameter.addParentType(new ConcreteClass("classes.typeParameter", "ClassTypeParameter"));
        concreteClass.addTypeParameter(classTypeParameter);

        //ANNOTATION
        Annotation annotation = new Annotation("classes.annotations", "ClassAnnotation");
        concreteClass.addAnnotation(new AnnotationInstance(annotation));

        //GENERIC SUPER CLASS
        ConcreteClass superClass = new ConcreteClass("classes.superclass", "SuperClass");
        TypeParameter superClassTypeParameter = new TypeParameter("C");
        ConcreteClass superClassTypeParameterParent = new ConcreteClass("classes.typeParameter", "SuperClassTypeParameterParent");
        superClassTypeParameter.addParentType(superClassTypeParameterParent);

        ParameterizedConcreteClass parameterizedSuperClass = new ParameterizedConcreteClass(superClass);
        parameterizedSuperClass.addParameterType(new ConcreteClass("classes.typeParameter", "SuperClassExtendedTypeParameterParent"));

        concreteClass.setSuperClass(parameterizedSuperClass);


        //GENERIC INTERFACE
        Interface interfaceDefinition = new Interface("classes.interfaces", "ClassInterface");
        AbstractMethod interfaceMethod = new AbstractMethod(AccessModifier.Public, Primitive.Int, "classInterfaceMethod" );
        interfaceDefinition.addMethod(interfaceMethod);

        TypeParameter interfaceTypeParameter = new TypeParameter();
        interfaceTypeParameter.addParentType(new ConcreteClass("classes.typeParameter", "InterfaceTypeParameter"));

        ParameterizedInterface parameterizedInterface = new ParameterizedInterface(interfaceDefinition);
        parameterizedInterface.addParameterType(new ConcreteClass("classes.typeParameter", "InterfaceExtendedTypeParameter"));

        concreteClass.addInterface(parameterizedInterface);
        concreteClass.addMethod(interfaceMethod.getConcreteCopy());

        //METHOD
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Private, new ConcreteClass("classes.methods.classes","ClassMethodReturnType"), "classMethod");
        method.addParamater(new Parameter(new ConcreteClass("classes.methods.classes", "ClassMethodParam1"), "param1"));

        TypeParameter parameterTypeParameter = new TypeParameter("S");
        ConcreteClass methodParameterType1 = new ConcreteClass("classes.methods.classes", "ClassMethodParameterType1");
        parameterTypeParameter.addParentType(methodParameterType1);
        ConcreteClass parameterizedClass = new ConcreteClass("classes.methods.classes", "ClassMethodParameterizedClass");
        parameterizedClass.addTypeParameter(parameterTypeParameter);
        ParameterizedType parameterizedType = new ParameterizedType(parameterizedClass){};
        parameterizedType.addParameterType(new ConcreteClass("classes.methods.classes", "ExtendedClassMethodParameterType1", methodParameterType1));

        TypeParameter parameterType = new TypeParameter("X");
        parameterType.addParentType(new ConcreteClass("classes.methods.classes", "ClassMethodParameterType2"));
        method.addParamater(new Parameter(parameterizedType, "param2"));
        method.addTypeParameter(parameterType);

        method.addAnnotation(new AnnotationInstance(new Annotation("classes.methods.annotations", "ClassMethodAnnotation1")));

        concreteClass.addMethod(method);

        //ATTRIBUTES
        concreteClass.addAttribute(new ClassAttribute(AccessModifier.Private, new ConcreteClass("classes.attributes", "ClassAttribute"),"classAttribute"));

        return concreteClass;
    }

    public static TypeValue typeValueFor(Type type){
        return new TypeValue(type) {
            @Override
            public String getCodeString() {
                return null;
            }
        };
    }

    public static Primitive getPrimitiveWithBoxable() {
        Primitive boxablePrimitive = new Primitive("PrimitiveWithBoxable", null);
        Boxable boxable = new Boxable("some.place", "BoxableWithPrimitive", boxablePrimitive);
        boxablePrimitive.setBoxableType(boxable);
        return boxablePrimitive;
    }

    public static Boxable getBoxableWithPrimitive() {
        Primitive primitive = new Primitive("primitiveForBoxable", null);
        Boxable boxableWithPrimitive = new Boxable("some.place", "BoxableWithoutPrimitive", primitive);
        primitive.setBoxableType(boxableWithPrimitive);
        return boxableWithPrimitive;
    }

    public static void main(String[] args) {
        System.out.println(TemplateUtil.template("java_file").add("javaDef", getAPECClass_CMAI()).add("imports", ImportUtil.importsForClass(getAPECClass_CMAI())).render());
    }
}

