package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.Boxable;
import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestPrimitive {

    @Test
    public void isCompatibleWith_UnboxablePrimitive(){
        Primitive primitive1 = new Primitive("somePrimitive", null);
        Primitive primitive2 = new Primitive("anotherPrimitive", null);

        assertNull("Should not have boxable type", primitive1.getBoxableType());
        assertNull("Should not have boxable type", primitive2.getBoxableType());

        assertFalse("Primitive is incompatible with null", primitive1.isCompatibleWith(null));
        assertTrue("Primitive is compatible with itself", primitive1.isCompatibleWith(TestUtil.typeValueFor(primitive1)));
        assertFalse("Primitive is not compatible with another primitive", primitive1.isCompatibleWith(TestUtil.typeValueFor(primitive2)));

    }


    @Test
    public void isCompatibleWith_BoxablePrimitive(){
        Primitive primitiveWithBoxable = TestUtil.getPrimitiveWithBoxable();

        assertThat(primitiveWithBoxable.getBoxableType(), notNullValue());
        assertTrue("Primitive should be compatible with its boxable", primitiveWithBoxable.isCompatibleWith(TestUtil.typeValueFor(primitiveWithBoxable.getBoxableType())));
    }

}
