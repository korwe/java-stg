package com.korwe.javastg.test.definition;

import com.korwe.javastg.definition.ConcreteClass;
import com.korwe.javastg.definition.Reference;
import com.korwe.javastg.definition.TypeParameter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestTypeParameter {

    @Test
    public void getCopy(){
        TypeParameter original = new TypeParameter("X");
        original.addParentType(new ConcreteClass("some.class.place", "TypeParameterClass"));

        assertFalse("Type parameter is required to have parents", original.getParentTypes().isEmpty());
        assertThat(original.getName(), notNullValue());
        TypeParameter copy = original.getCopy();

        assertThat(copy.getName(), equalTo(original.getName()));
        for(Reference reference : original.getParentTypes()){
            assertTrue(copy.getParentTypes().contains(reference));
        }
    }

    @Test
    public void canAssign(){
        ConcreteClass parentType = new ConcreteClass("some.place", "ParentType");
        TypeParameter typeParameter = new TypeParameter();
        typeParameter.addParentType(parentType);

        assertFalse("Type parameter is required to have parent types", typeParameter.getParentTypes().isEmpty());

        assertTrue(typeParameter.canAssign(new ConcreteClass("some.place", "ExtendedType", parentType)));

    }
}
