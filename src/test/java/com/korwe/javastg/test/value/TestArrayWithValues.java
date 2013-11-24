package com.korwe.javastg.test.value;

import com.korwe.javastg.type.ConcreteClass;
import com.korwe.javastg.type.PrimitiveType;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.ArrayWithValues;
import com.korwe.javastg.value.ConstructorValue;
import com.korwe.javastg.value.LiteralValue;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestArrayWithValues {

    @Test
    public void testArrayWithPrimitives(){

        int[] arrayValues = new int[]{1,1,2,3,5};
        PrimitiveType primitiveType = PrimitiveType.Int;
        ArrayWithValues arrayWithValues = new ArrayWithValues(primitiveType);
        for(int x : arrayValues){
            arrayWithValues.addArrayValue(new LiteralValue(x));
        }

        assertEquals("Incorrect codeString for arrayWithValues(primitives) ", String.format("new %s[]{%d, %d, %d, %d, %d}",primitiveType.getName(), arrayValues[0], arrayValues[1], arrayValues[2], arrayValues[3], arrayValues[4]), arrayWithValues.getCodeString());
    }

    @Test
    public void testArrayWithConstructorValues(){
        ConcreteClass concreteClass = TestUtil.getBasicConcreteClass();
        ConstructorValue[] values = new ConstructorValue[]{new ConstructorValue(concreteClass), new ConstructorValue(concreteClass), new ConstructorValue(concreteClass)};
        ArrayWithValues arrayWithValues = new ArrayWithValues(concreteClass);
        for(ConstructorValue v: values){
            arrayWithValues.addArrayValue(v);
        }

        assertEquals("Incorrect codeString for arrayWithValues(references)", String.format("new %s[]{%s, %s, %s}", concreteClass.getName(), values[0], values[1], values[2]), arrayWithValues.getCodeString());
    }




}
