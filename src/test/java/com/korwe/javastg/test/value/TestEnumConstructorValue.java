package com.korwe.javastg.test.value;

import com.korwe.javastg.exception.NoConstructorFoundException;
import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Enum;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.ConstructorValue;
import com.korwe.javastg.value.EnumConstructorValue;
import com.korwe.javastg.value.LiteralValue;
import com.korwe.javastg.value.TypeDefinitionValue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestEnumConstructorValue {
    @Test(expected = NoConstructorFoundException.class)
    public void enumWithoutConstructorShouldThrowNoConstructorException(){
        Enum enumDef = TestUtil.getBasicEnum();
        ConstructorValue constructorValue = new EnumConstructorValue(enumDef);
        constructorValue.getCodeString();
    }

    @Test
    public void enumWithConstructorArgArray(){
        Enum enumDef = TestUtil.getEnumWithConstructor();
        String stringValue = "someString";
        ConstructorValue constructorValue = new EnumConstructorValue(enumDef, new LiteralValue(BoxableType.String, stringValue));
        assertEquals("Incorrect codeString generated for enum constructor value(enum with constructor args)", String.format("%s(\"%s\")", enumDef.getName(), stringValue), constructorValue.getCodeString());
    }

    @Test
    public void enumWithMultipleConstructorArgsArray(){
        Enum enumDef = TestUtil.getEnumWithMultiArgConstructor();
        char charValue = 'x';
        double doubleValue = 10.0;
        EnumConstructorValue enumConstructorValue = new EnumConstructorValue(enumDef, new LiteralValue(PrimitiveType.Char, charValue), new LiteralValue(PrimitiveType.Double, doubleValue));
        assertEquals("Incorrect codeString generated for enum constructor value(enum with multiple args constructor)", String.format("%s('%s', %.1f)", enumDef.getName(), charValue, doubleValue), enumConstructorValue.getCodeString());
    }

    @Test
    public void enumWithMultipleConstructorArgsMap(){
        Enum enumDef = TestUtil.getEnumWithMultiArgConstructor();
        char charValue = '3';
        double doubleValue = 1.633;
        Map<String, TypeDefinitionValue> values = new HashMap<>();
        values.put("charValue", new LiteralValue(PrimitiveType.Char, charValue));
        values.put("doubleValue", new LiteralValue(PrimitiveType.Double, doubleValue));

        ConstructorValue constructorValue = new EnumConstructorValue(enumDef, values);
        assertEquals("Incorrect codeString generated for enum constructor value(enum with multiple args constructor)", String.format("%s('%s', %.3f)", enumDef.getName(), charValue, doubleValue), constructorValue.getCodeString());
    }
}
