package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.TypeValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestPrimitive {

    @Test
    public void canAssign_UnboxablePrimitive(){
        Primitive primitive1 = new Primitive("somePrimitive", null);
        Primitive primitive2 = new Primitive("anotherPrimitive", null);

        assertNull("Should not have boxable type", primitive1.getBoxableType());
        assertNull("Should not have boxable type", primitive2.getBoxableType());

        assertFalse("Primitive is incompatible with null", primitive1.canAssign((TypeValue) null));
        assertTrue("Primitive is compatible with itself", primitive1.canAssign(TestUtil.typeValueFor(primitive1)));
        assertFalse("Primitive is not compatible with another primitive", primitive1.canAssign(TestUtil.typeValueFor(primitive2)));

    }


    @Test
    public void canAssign_BoxablePrimitive(){
        Primitive primitiveWithBoxable = TestUtil.getPrimitiveWithBoxable();

        assertThat(primitiveWithBoxable.getBoxableType(), notNullValue());
        assertTrue("Primitive should be compatible with its boxable", primitiveWithBoxable.canAssign(TestUtil.typeValueFor(primitiveWithBoxable.getBoxableType())));
    }

}
