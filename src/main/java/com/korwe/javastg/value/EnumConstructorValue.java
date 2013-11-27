package com.korwe.javastg.value;

import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.util.TemplateUtil;
import org.stringtemplate.v4.ST;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class EnumConstructorValue extends ConstructorValue{

    public EnumConstructorValue(TypeDefinition typeDefinition) {
        super(typeDefinition);
    }

    public EnumConstructorValue(TypeDefinition typeDefinition, TypeValue... constructorArgArray) {
        super(typeDefinition, constructorArgArray);
    }

    public EnumConstructorValue(TypeDefinition typeDefinition, List<TypeValue> constructorArgsList) {
        super(typeDefinition, constructorArgsList);
    }

    public EnumConstructorValue(TypeDefinition typeDefinition, Map<String, TypeValue> constructorArgMap) {
        super(typeDefinition, constructorArgMap);
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("enum_constructor_value");
        template.add("type", getType().getName());
        template.add("args", constructorArguments());
        return template.render();
    }
}
