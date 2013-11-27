package com.korwe.javastg.test.value;

import com.korwe.javastg.definition.Boxable;
import com.korwe.javastg.definition.ConcreteClass;
import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.ArrayValue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestArrayValue {

    @Test
    public void testPrimitiveTypeCodeString(){
        int arraySize = 4;
        ArrayValue arrayValue = new ArrayValue(Primitive.Boolean,arraySize);
        assertEquals("Incorrect value generated from template", String.format("new boolean[%d]", arraySize), arrayValue.getCodeString());
    }

    @Test
    public void testConcreteClassTypeCodeString(){
        int arraySize = 6;
        ConcreteClass klass = TestUtil.getBasicConcreteClass();
        ArrayValue arrayValue = new ArrayValue(klass, arraySize);
        assertEquals("Incorrect codeString generated for concrete class", String.format("new %s[%d]", klass.getName(), arraySize), arrayValue.getCodeString());
    }

    @Test
    public void testBoxableTypeCodeString(){
        int arraySize = 3;
        ArrayValue arrayValue = new ArrayValue(Boxable.Double ,arraySize);
        assertEquals("Incorrect codeString generated for boxableType", String.format("new Double[%d]",arraySize), arrayValue.getCodeString());
    }
}
