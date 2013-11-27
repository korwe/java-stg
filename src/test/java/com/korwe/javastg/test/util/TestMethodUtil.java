package com.korwe.javastg.test.util;

import com.korwe.javastg.definition.AccessModifier;
import com.korwe.javastg.definition.ClassAttribute;
import com.korwe.javastg.definition.ConcreteMethod;
import com.korwe.javastg.definition.Parameter;
import com.korwe.javastg.util.MethodUtil;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestMethodUtil {

    @Test
    public void setterMethod(){
        ClassAttribute attribute = new ClassAttribute(TestUtil.getBasicConcreteClass(), "myAttribute");
        ConcreteMethod setter = MethodUtil.setterMethod(attribute);
        assertThat(setter, notNullValue());
        assertEquals("Setter should default to public", setter.getAccessModifier(), AccessModifier.Public);
        assertThat(setter.getName(), equalTo(String.format("set%s",attribute.getCapitalizedName())));
        assertFalse("No parameters were added", setter.getParameters().isEmpty());
        assertEquals("Only 1 parameter should have been added to setter", 1, setter.getParameters().size());
        Parameter parameter = setter.getParameters().get(0);
        assertEquals("Parameters type should be same as provided attribute", attribute.getType(), parameter.getType());
        assertTrue("Parameter shouldn't have any annotations", parameter.getAnnotations().isEmpty());
        assertFalse("Parameter shouldn't default to final", parameter.isFinal());
        assertThat(setter.getBody(), equalTo(String.format("this.%s = %s;", attribute.getName(), attribute.getName())));

        assertTrue("No type parameters should be added to setter", setter.getTypeParameters().isEmpty());
        assertTrue("No annotations should be added to setter", setter.getAnnotations().isEmpty());

    }

    @Test
    public void getterMethod(){
        ClassAttribute attribute = new ClassAttribute(TestUtil.getBasicConcreteClass(), "myAttribute");
        ConcreteMethod getter = MethodUtil.getterMethod(attribute);
        assertThat(getter, notNullValue());
        assertEquals("Getter should default to public", getter.getAccessModifier(), AccessModifier.Public);
        assertThat(getter.getName(), equalTo(String.format("get%s", attribute.getCapitalizedName())));
        assertThat(getter.getBody(), equalTo(String.format("return this.%s;", attribute.getName())));

        assertTrue("No parameters should be added", getter.getParameters().isEmpty());
        assertTrue("No type parameters should be added to getter", getter.getTypeParameters().isEmpty());
        assertTrue("No annotations should be added to getter", getter.getAnnotations().isEmpty());

    }
}
