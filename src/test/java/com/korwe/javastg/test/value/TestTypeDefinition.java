package com.korwe.javastg.test.value;

import com.korwe.javastg.definition.Primitive;
import com.korwe.javastg.util.TestUtil;
import com.korwe.javastg.value.*;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestTypeDefinition {

    @Test
    public void isLiteralValue(){
        assertTrue((new LiteralValue("hi")).isLiteralValue());
        assertFalse(typeDefinitionValue().isLiteralValue());
    }

    @Test
    public void isArrayValue(){
        assertTrue((new ArrayValue(Primitive.Short, 5)).isArrayValue());
        assertFalse(typeDefinitionValue().isArrayValue());
    }

    @Test
    public void isArrayWithValues(){
        assertTrue((new ArrayWithValues(Primitive.Char)).isArrayWithValues());
        assertFalse(typeDefinitionValue().isArrayWithValues());
    }

    @Test
    public void isConstructorValue(){
        assertTrue((new ConstructorValue(TestUtil.getBasicConcreteClass())).isConstructorValue());
        assertFalse(typeDefinitionValue().isConstructorValue());
    }

    @Test
    public void isEnumConstructorValue(){
        assertTrue((new EnumConstructorValue(TestUtil.getBasicEnum())).isEnumConstructorValue());
        assertFalse(typeDefinitionValue().isEnumConstructorValue());

    }

    private TypeValue typeDefinitionValue(){
        return new TypeValue() {
            @Override
            public String getCodeString() {
                return null;
            }
        };
    }
}
