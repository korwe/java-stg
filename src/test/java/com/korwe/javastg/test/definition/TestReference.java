package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.Reference;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.TypeValue;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestReference {

    @Test
    public void isCompatibleWith(){
        Reference reference = new Reference("some.place", "SomeReference");
        assertTrue("Reference should be compatible with null", reference.isCompatibleWith((TypeValue)null));
        assertTrue("Reference should be compatible with itself", reference.isCompatibleWith(TestUtil.typeValueFor(reference)));
    }

    @Test
    public void packagesEqual(){
        Reference reference1 = new Reference("some.wonderful.place", "Reference1");
        Reference reference2 = new Reference("some.wonderful.place", "Reference2");

        assertFalse("References should not be equal", reference1.equals(reference2));
        assertTrue("Packages should be equal", reference1.getPackageName().equals(reference2.getPackageName()));
        assertTrue("Packages should be equal", reference1.packageEqual(reference2));

        assertFalse("null should not be equal", reference1.packageEqual(new Reference(null, "SomeReference")));
    }

}
