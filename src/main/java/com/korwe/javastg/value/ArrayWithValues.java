package com.korwe.javastg.value;

import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.util.TemplateUtil;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ArrayWithValues extends TypeValue {

    private List<TypeValue> arrayValues;

    public ArrayWithValues(TypeDefinition typeDefinition){
        super(typeDefinition);
        this.arrayValues = new ArrayList<>();
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("array_with_values");
        template.add("type", getType());
        template.add("values", arrayValues);
        return template.render();
    }

    public ArrayWithValues(TypeDefinition typeDefinition, List<TypeValue> arrayValues) {
        super(typeDefinition);
        this.arrayValues = arrayValues;
    }

    public List<TypeValue> getArrayValues() {
        return arrayValues;
    }

    public void setArrayValues(List<TypeValue> arrayValues) {
        this.arrayValues = arrayValues;
    }

    public void addArrayValue(TypeValue arrayValue){
        this.arrayValues.add(arrayValue);
    }
}
