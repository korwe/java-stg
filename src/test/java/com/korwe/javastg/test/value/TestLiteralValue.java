package com.korwe.javastg.test.value;

import com.korwe.javastg.type.BoxableType;
import com.korwe.javastg.value.LiteralValue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestLiteralValue {

    @Test
    public void codeStringOfLiteral(){
        String literalString = "0x55";
        LiteralValue lv = new LiteralValue(literalString);
        assertEquals("Incorrect codeString generated for literal value(literal string)", literalString, lv.getCodeString());
    }

    @Test
    public void codeStringOfLiteralString(){
        String stringValue = "test string value";
        LiteralValue lv = new LiteralValue(stringValue, true);
        assertEquals("Incorrect codeString generated for literal value(literal string)", String.format("\"%s\"",stringValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfPrimitiveShort(){
        short shortValue = 5;
        LiteralValue lv = new LiteralValue(shortValue);
        assertEquals("Incorrect codeString generated for literal value(primitive short)", String.format("%d",shortValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfShortObject(){
        Short shortValue = 100;
        LiteralValue lv = new LiteralValue(shortValue);
        assertEquals("Incorrect codeString generated for literal value(short object)", String.format("%d",shortValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfPrimitiveInt(){
        Integer integerValue = 20;
        LiteralValue lv = new LiteralValue(integerValue);
        assertEquals("Incorrect codeString generated for literal value(primitive int)", String.format("%d",integerValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfIntegerObject(){
        Integer integerValue = 5;
        LiteralValue lv = new LiteralValue(integerValue);
        assertEquals("Incorrect codeString generated for literal value(integer object)", String.format("%d",integerValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfPrimitiveLong(){
        long longValue = 551523512353L;
        LiteralValue lv = new LiteralValue(longValue);
        assertEquals("Incorrect codeString generated for literal value(primitive long)", String.format("%dL",longValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfLongObject(){
        Long longValue = 523464363246L;
        LiteralValue lv = new LiteralValue(longValue);
        assertEquals("Incorrect codeString generated for literal value(long object)", String.format("%dL",longValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfPrimitiveFloat(){
        float floatValue = 5.634f;
        LiteralValue lv = new LiteralValue(floatValue);
        assertEquals("Incorrect codeString generated for literal value(primitive float)", String.format("%.3ff",floatValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfFloatObject(){
        Float floatValue = 7.2341f;
        LiteralValue lv = new LiteralValue(floatValue);
        assertEquals("Incorrect codeString generated for literal value(float object)", String.format("%.4ff",floatValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfPrimitiveDouble(){
        double doubleValue = 5.346;
        LiteralValue lv = new LiteralValue(doubleValue);
        assertEquals("Incorrect codeString generated for literal value(primitive double)", String.format("%.3f",doubleValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfDoubleObject(){
        Double doubleValue = 5.463;
        LiteralValue lv = new LiteralValue(doubleValue);
        assertEquals("Incorrect codeString generated for literal value(primitive double)", String.format("%.3f",doubleValue), lv.getCodeString());
    }

    @Test
    public void codeStringOfPrimitiveChar(){
        char charValue = 'c';
        LiteralValue lv = new LiteralValue(charValue);
        assertEquals("Incorrect codeString generated for literal value(primitive char)", String.format("'%s'",charValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfCharacterObject(){
        Character charValue = 'l';
        LiteralValue lv = new LiteralValue(charValue);
        assertEquals("Incorrect codeString generated for literal value(character object)", String.format("'%s'",charValue), lv.getCodeString());

    }


    @Test
    public void codeStringOfPrimitiveBoolean(){
        boolean booleanValue = false;
        LiteralValue lv = new LiteralValue(booleanValue);
        assertEquals("Incorrect codeString generated for literal value(primitive boolean)", String.format("%b",booleanValue), lv.getCodeString());

    }

    @Test
    public void codeStringOfBooleanObject(){
        Boolean booleanValue = true;
        LiteralValue lv = new LiteralValue(booleanValue);
        assertEquals("Incorrect codeString generated for literal value(boolean object)", String.format("%b",booleanValue), lv.getCodeString());

    }

}
