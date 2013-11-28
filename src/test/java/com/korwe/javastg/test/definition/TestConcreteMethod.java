package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestConcreteMethod {

    @Test
    public void getAbstractCopy(){
        ConcreteMethod concreteMethod = new ConcreteMethod(AccessModifier.Public, new ConcreteClass("some.place", "SomeClass"), "someMethod");

        concreteMethod.addParamater(new Parameter(new ConcreteClass("some.place", "ParameterClass"), "myParam"));
        concreteMethod.addParamater(new Parameter(new ConcreteClass("some.place", "AnotherParameter"), "anotherParam"));

        TypeParameter typeParameter = new TypeParameter("J");
        typeParameter.addParentType(new ConcreteClass("some.place", "ParentTypeClass"));
        concreteMethod.addTypeParameter(typeParameter);

        assertThat(concreteMethod.getReturnType(), notNullValue());
        assertThat(concreteMethod.getName(), notNullValue());
        assertFalse(concreteMethod.getParameters().isEmpty());
        assertFalse(concreteMethod.getTypeParameters().isEmpty());

        AbstractMethod abstractMethod = concreteMethod.getAbstractCopy();



        assertEquals("Access should be equal", concreteMethod.getAccessModifier(), abstractMethod.getAccessModifier());
        assertEquals("Method name should be equal", concreteMethod.getName(), abstractMethod.getName());
        assertEquals("ReturnType should be equal", concreteMethod.getReturnType(), abstractMethod.getReturnType());

        assertEquals("Incorrect number of parameters were copied", concreteMethod.getParameters().size(), abstractMethod.getParameters().size());
        for(Parameter parameter : concreteMethod.getParameters()){
            assertTrue("Copied method does not contain all parameters", abstractMethod.getParameters().contains(parameter));
            for(Parameter copiedParameter : abstractMethod.getParameters()){
                assertFalse("No parameters should be same instance", copiedParameter == parameter);
            }
        }

        assertEquals("Incorrect number of type parameters were copied", concreteMethod.getTypeParameters().size(), abstractMethod.getTypeParameters().size());
        for(TypeParameter typeParam : concreteMethod.getTypeParameters()){
            for(TypeParameter concreteMethodTypeParam : abstractMethod.getTypeParameters()){
                assertTrue("Type parameters should be same instance as abstract method", typeParam == concreteMethodTypeParam);
            }
        }

        //Make sure static is copied correctly
        assertEquals("Static type should be the equal", concreteMethod.isStatic(), abstractMethod.isStatic());
        concreteMethod.setStatic(!concreteMethod.isStatic());

        ConcreteMethod anotherCopiedMethod = concreteMethod.getCopy();
        assertEquals("Static type should be the equal", concreteMethod.isStatic(), anotherCopiedMethod.isStatic());
    }

    @Test
    public void getCopy(){

        ConcreteMethod originalMethod = new ConcreteMethod(AccessModifier.Public, new ConcreteClass("some.place", "SomeClass"), "someMethod");

        originalMethod.addParamater(new Parameter(new ConcreteClass("some.place", "ParameterClass"), "myParam"));
        originalMethod.addParamater(new Parameter(new ConcreteClass("some.place", "AnotherParameter"), "anotherParam"));

        TypeParameter typeParameter = new TypeParameter("K");
        typeParameter.addParentType(new ConcreteClass("some.place", "ParentTypeClass"));
        originalMethod.addTypeParameter(typeParameter);

        assertThat(originalMethod.getReturnType(), notNullValue());
        assertThat(originalMethod.getName(), notNullValue());
        assertFalse(originalMethod.getParameters().isEmpty());
        assertFalse(originalMethod.getTypeParameters().isEmpty());

        ConcreteMethod copiedMethod = originalMethod.getCopy();



        assertEquals("Access should be equal", originalMethod.getAccessModifier(), copiedMethod.getAccessModifier());
        assertEquals("Method name should be equal", originalMethod.getName(), copiedMethod.getName());
        assertEquals("ReturnType should be equal", originalMethod.getReturnType(), copiedMethod.getReturnType());

        assertEquals("Incorrect number of parameters were copied", originalMethod.getParameters().size(), copiedMethod.getParameters().size());
        for(Parameter parameter : originalMethod.getParameters()){
            assertTrue("Copied method does not contain all parameters", copiedMethod.getParameters().contains(parameter));
            for(Parameter copiedParameter : copiedMethod.getParameters()){
                assertFalse("No parameters should be same instance", copiedParameter == parameter);
            }
        }

        assertEquals("Incorrect number of type parameters were copied", originalMethod.getTypeParameters().size(), copiedMethod.getTypeParameters().size());
        for(TypeParameter typeParam : originalMethod.getTypeParameters()){
            for(TypeParameter concreteMethodTypeParam : copiedMethod.getTypeParameters()){
                assertFalse("Type parameters should be same instance as copy method", typeParam == concreteMethodTypeParam);
            }
        }

        //Make sure static is copied correctly
        assertEquals("Static type should be the equal", originalMethod.isStatic(), copiedMethod.isStatic());
        originalMethod.setStatic(!originalMethod.isStatic());

        ConcreteMethod anotherCopiedMethod = originalMethod.getCopy();
        assertEquals("Static type should be the equal", originalMethod.isStatic(), anotherCopiedMethod.isStatic());



    }

}
