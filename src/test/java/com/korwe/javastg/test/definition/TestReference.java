package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.Boxable;
import com.korwe.javastg.definition.Reference;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestReference {

    @Test
    public void isCompatibleWith(){
        Reference reference = new Reference("some.place", "SomeReference");
        assertTrue("Reference should be compatible with null", reference.isCompatibleWith(null));
        assertTrue("Reference should be compatible with itself", reference.isCompatibleWith(TestUtil.typeValueFor(reference)));
    }

}
