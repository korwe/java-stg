package com.korwe.javastg.test.value;

import com.korwe.javastg.exception.NoConstructorFoundException;
import com.korwe.javastg.type.BoxableType;
import com.korwe.javastg.type.ConcreteClass;
import com.korwe.javastg.type.PrimitiveType;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.ConstructorValue;
import static org.junit.Assert.*;

import com.korwe.javastg.value.LiteralValue;
import com.korwe.javastg.value.TypeDefinitionValue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestConstructorValue {

    @Test
    public void testBoxableType(){
        BoxableType boxableType = BoxableType.Float;
        ConstructorValue constructorValue = new ConstructorValue(boxableType);
        assertEquals("Incorrect codeString generated for constructor value(boxable)", String.format("new %s()", boxableType.getName()), constructorValue.getCodeString());
    }

    @Test
    public void testBasicClass(){
        ConcreteClass concreteClass = TestUtil.getBasicConcreteClass();
        ConstructorValue constructorValue = new ConstructorValue(concreteClass);
        assertEquals("Incorrect codeString generated for constructor value(basic classs)", String.format("new %s()", concreteClass.getName()), constructorValue.getCodeString());
    }

    @Test
    public void testClassWithConstructorArgArray(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithConstructor();
        int intValue = 15;
        ConstructorValue constructorValue = new ConstructorValue(concreteClass, new LiteralValue(PrimitiveType.Int, intValue));
        assertEquals("Incorrect codeString generated for constructor value(class with constructor args)", String.format("new %s(%d)", concreteClass.getName(), intValue), constructorValue.getCodeString());
    }

    @Test
    public void testClassWithMultipleConstructorArgsArray(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithMultiArgConstructor();
        int intValue = 5;
        long longValue = 10;
        ConstructorValue constructorValue = new ConstructorValue(concreteClass, new LiteralValue(PrimitiveType.Int, intValue), new LiteralValue(PrimitiveType.Long, longValue));
        assertEquals("Incorrect codeString generated for constructor value(class with multiple args constructor)", String.format("new %s(%d, %dL)", concreteClass.getName(), intValue, longValue), constructorValue.getCodeString());
    }

    @Test
    public void testClassWithMultipleConstructorArgsMap(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithMultiArgConstructor();
        int intValue = 3;
        long longValue = 1;
        Map<String, TypeDefinitionValue> values = new HashMap<>();
        values.put("intValue", new LiteralValue(PrimitiveType.Int, intValue));
        values.put("longValue", new LiteralValue(PrimitiveType.Long, longValue));

        ConstructorValue constructorValue = new ConstructorValue(concreteClass, values);
        assertEquals("Incorrect codeString generated for constructor value(class with multiple args constructor)", String.format("new %s(%d, %dL)", concreteClass.getName(), intValue, longValue), constructorValue.getCodeString());
    }

    @Test
    public void shouldIgnoreExtraMapValueForMultipleConstructorArgs(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithMultiArgConstructor();
        int intValue = 100;
        long longValue = 155L;
        Map<String, TypeDefinitionValue> values = new HashMap<>();
        values.put("intValue", new LiteralValue(PrimitiveType.Int, intValue));
        values.put("longValue", new LiteralValue(PrimitiveType.Long, longValue));

        ConstructorValue constructorValue = new ConstructorValue(concreteClass, values);
        assertEquals("Incorrect codeString generated for constructor value(class with multiple args constructor)", String.format("new %s(%d, %dL)", concreteClass.getName(), intValue, longValue), constructorValue.getCodeString());
    }

    @Test(expected = NoConstructorFoundException.class)
    public void multipleConstructorArgsMissingMapKeys(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithMultiArgConstructor();
        int intValue = 67;
        long longValue = 92L;
        Map<String, TypeDefinitionValue> values = new HashMap<>();
        values.put("intValue", new LiteralValue(PrimitiveType.Int, intValue));
        values.put("longValueIncorrect", new LiteralValue(PrimitiveType.Long, longValue));

        ConstructorValue constructorValue = new ConstructorValue(concreteClass, values);
        constructorValue.getCodeString();
    }

    @Test(expected = NoConstructorFoundException.class)
    public void multipleConstructorArgsIncorrectArrayOrder(){
        ConcreteClass concreteClass = TestUtil.getConcreteClassWithMultiArgConstructor();
        int intValue = 5;
        long longValue = 10;
        ConstructorValue constructorValue = new ConstructorValue(concreteClass, new LiteralValue(PrimitiveType.Long, longValue),new LiteralValue(PrimitiveType.Int, intValue));
        constructorValue.getCodeString();
    }




}
