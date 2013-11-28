package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.Boxable;
import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.TypeValue;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestBoxable {

    @Test
    public void canAssign(){
        Boxable boxableWithoutPrimitive = new Boxable("some.place", "BoxableWithoutPrimitive", null);
        Primitive primitive = new Primitive("myPrimitive",null);

        assertTrue("Boxable should be compatible with null", boxableWithoutPrimitive.canAssign((TypeValue) null));
        assertFalse("Boxable with null primitive should not fail Primitive comparison", boxableWithoutPrimitive.canAssign(TestUtil.typeValueFor(primitive)));
    }

    @Test
    public void canAssign_BoxableWithoutPrimitive(){
        Boxable boxableWithPrimitive = TestUtil.getBoxableWithPrimitive();
        assertThat(boxableWithPrimitive.getPrimitiveType(), notNullValue());

        assertTrue("Boxable should be compatible with null", boxableWithPrimitive.canAssign((TypeValue) null));
        assertTrue("Boxable should be compatible with its primitive", boxableWithPrimitive.canAssign(TestUtil.typeValueFor(boxableWithPrimitive.getPrimitiveType())));
        Primitive wrongPrimitive = new Primitive("testPrimitive", null);
        assertThat(wrongPrimitive, not(equalTo( boxableWithPrimitive.getPrimitiveType())));
        assertFalse("Boxable should only be compatible with its primitive", boxableWithPrimitive.canAssign(TestUtil.typeValueFor(wrongPrimitive)));
    }


}
