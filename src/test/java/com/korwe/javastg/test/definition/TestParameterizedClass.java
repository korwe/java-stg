package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.*;
import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;
import com.korwe.javastg.util.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestParameterizedClass {
    Class extendedClass;
    Class baseClass;
    Class extendedExtendedClass;
    ParameterizedClass parameterizedClass;

    @Before
    public void init(){
        baseClass = TestUtil.getBasicConcreteClass();
        extendedClass = new ConcreteClass("some.other.wierd.place", "ExtendedClass", baseClass);
        extendedExtendedClass = new ConcreteClass("some.weird.place", "ExtendedExtendedClass", extendedClass);
        parameterizedClass = new ParameterizedClass(extendedExtendedClass) {};

        assertTrue(baseClass.getTypeParameters().isEmpty());
        assertThat(extendedClass.getSuperClass(), notNullValue());
        assertTrue(extendedClass.getTypeParameters().isEmpty());
        assertThat(extendedExtendedClass.getSuperClass(), notNullValue());
        assertTrue(extendedExtendedClass.getTypeParameters().isEmpty());

    }

    @Test
    public void isCompatibleWith(){

        assertTrue("Base class should be compatible with any descendant class", baseClass.isCompatibleWith(TestUtil.typeValueFor(extendedExtendedClass)));
        assertFalse("Decendents should not be compatible with base class", parameterizedClass.isCompatibleWith(TestUtil.typeValueFor(baseClass)));



    }

    @Test
    public void isCompatibleWith_parameterizedTypeExtendsGenerifiedClassWithTypeParameter(){
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();
        extendedExtendedClass.addTypeParameter(typeParameter);
        assertTrue("Base class without type parameters should be compatible with any descendant class - regardless of decendant's type parameters", baseClass.isCompatibleWith(TestUtil.typeValueFor(parameterizedClass)));
    }

    @Test
    public void isCompatibleWith_parameterTypesAreNotRequired(){
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();
        baseClass.addTypeParameter(typeParameter);

        assertTrue(parameterizedClass.getParameterTypes().isEmpty());
        assertTrue("You should not be required to provide parameter types", baseClass.isCompatibleWith(TestUtil.typeValueFor(parameterizedClass)));

    }

    @Test
    public void isCompatibleWith_parameterTypesWithUnmatchingTypeParameters(){
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();
        baseClass.addTypeParameter(typeParameter);

        parameterizedClass.addParameterType(new Reference("garbage.place", "UnmatchingParameterType"));

        assertFalse("parameter types should not be compatible with ancestor class' type paramters", baseClass.isCompatibleWith(TestUtil.typeValueFor(parameterizedClass)));

    }

    @Test
    public void isCompatibleWith_parameterTypesWithMatchingTypeParameters(){
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();
        baseClass.addTypeParameter(typeParameter);

        for(Reference referenceType : typeParameter.getParentTypes()){
            parameterizedClass.addParameterType(referenceType);
        }

        assertTrue("parameter types should be compatible with ancestor class' type paramters", baseClass.isCompatibleWith(TestUtil.typeValueFor(parameterizedClass)));

    }

    @Test
    public void isCompatibleWith_parameterTypesWithExtendedTypeParameters(){
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();

        baseClass.addTypeParameter(typeParameter);

        for(Reference referenceType : typeParameter.getParentTypes()){
            if(ClassType.class.isAssignableFrom(referenceType.getClass())){
                parameterizedClass.addParameterType(new ConcreteClass(String.format("extended.%s",referenceType.getPackageName()), String.format("Extended%s", referenceType.getName()), (ClassType)referenceType));
            }
            else if(InterfaceType.class.isAssignableFrom(referenceType.getClass())){
                parameterizedClass.addParameterType(new Interface(String.format("extended.%s",referenceType.getPackageName()), String.format("Extended%s", referenceType.getName()), (InterfaceType)referenceType));
            }
            else{
                parameterizedClass.addParameterType(referenceType);
            }
        }

        assertTrue("parameter types should be compatible with ancestor class' type paramters", baseClass.isCompatibleWith(TestUtil.typeValueFor(parameterizedClass)));

    }
}
