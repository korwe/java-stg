package com.korwe.javastg.test.type;

import com.korwe.javastg.definition.ConcreteClass;
import com.korwe.javastg.type.Class;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestClass {

    @Test
    public void isCompatibleWith(){
        Class extendedClass = TestUtil.getBasicExtendedClass();
        assertThat(extendedClass.getSuperClass(), notNullValue());

        Class extendedExtendedClass = new ConcreteClass("some.weird.place", "ExtendedExtendedClass", extendedClass);
        assertThat(extendedExtendedClass.getSuperClass(), notNullValue());

        assertTrue("Base class should be compatible with any descendant class", extendedExtendedClass.getSuperClass().getSuperClass().canAssign(TestUtil.typeValueFor(extendedExtendedClass)));
        assertFalse("Decendents should not be compatible with base class", extendedExtendedClass.canAssign(TestUtil.typeValueFor(extendedExtendedClass.getSuperClass().getSuperClass())));

    }
}

