package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.*;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestAbstractMethod {
    @Test
    public void getConcreteCopy(){
        AbstractMethod abstractMethod = new AbstractMethod(AccessModifier.Private, TestUtil.getBasicConcreteClass(), "myMethod");
        abstractMethod.addParamater(new Parameter(new ConcreteClass("some.place", "SomeClass"), "someParameter"));
        abstractMethod.addParamater(new Parameter(new ConcreteClass("some.place", "AnotherClass"), "anotherParameter"));

        TypeParameter typeParameter = new TypeParameter("X");
        typeParameter.addParentType(new ConcreteClass("some.place","TypeParamClass"));
        abstractMethod.addTypeParameter(typeParameter);

        assertThat(abstractMethod.getReturnType(), notNullValue());
        assertThat(abstractMethod.getName(), notNullValue());
        assertFalse(abstractMethod.getParameters().isEmpty());
        assertFalse(abstractMethod.getTypeParameters().isEmpty());


        ConcreteMethod concreteMethod = abstractMethod.getConcreteCopy();

        assertEquals("Access should be equal", abstractMethod.getAccessModifier(), concreteMethod.getAccessModifier());
        assertEquals("Method name should be equal", abstractMethod.getName(), concreteMethod.getName());
        assertEquals("ReturnType should be equal", abstractMethod.getReturnType(), concreteMethod.getReturnType());

        assertEquals("Incorrect number of parameters were copied", abstractMethod.getParameters().size(), concreteMethod.getParameters().size());
        for(Parameter parameter : abstractMethod.getParameters()){
            assertTrue("Copied method does not contain all parameters", concreteMethod.getParameters().contains(parameter));
            for(Parameter copiedParameter : concreteMethod.getParameters()){
                assertFalse("No parameters should be same instance", copiedParameter == parameter);
            }
        }

        assertEquals("Incorrect number of type parameters were copied", abstractMethod.getTypeParameters().size(), concreteMethod.getTypeParameters().size());
        for(TypeParameter typeParam : abstractMethod.getTypeParameters()){
            for(TypeParameter concreteMethodTypeParam : concreteMethod.getTypeParameters()){
                assertTrue("Type parameters should be same instance as abstract method", typeParam == concreteMethodTypeParam);
            }
        }


        //Make sure static is copied correctly
        assertEquals("Static type should be the equal", abstractMethod.isStatic(), concreteMethod.isStatic());
        abstractMethod.setStatic(!abstractMethod.isStatic());

        ConcreteMethod anotherCopiedMethod = abstractMethod.getConcreteCopy();
        assertEquals("Static type should be the equal", abstractMethod.isStatic(), anotherCopiedMethod.isStatic());

    }
}
