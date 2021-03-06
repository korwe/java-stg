package com.korwe.javastg.value;

import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.util.TemplateUtil;
import org.stringtemplate.v4.ST;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ArrayValue extends TypeValue {
    private Integer arraySize;
    //TODO: Support multi-dimensional array

    public ArrayValue(TypeDefinition typeDefinition, Integer arraySize) {
        super(typeDefinition);
        this.arraySize = arraySize;
    }

    public Integer getArraySize() {
        return arraySize;
    }

    public void setArraySize(Integer arraySize) {
        this.arraySize = arraySize;
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("array_value");
        template.add("type", getType());
        template.add("arraySize", arraySize);
        return template.render();
    }
}
