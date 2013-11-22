package com.korwe.javastg.value;

import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.util.TemplateUtil;
import org.stringtemplate.v4.ST;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConstructorValue extends TypeDefinitionValue{

    private TypeDefinitionValue[] constructorArgsArray;
    private Map<String, TypeDefinitionValue> constructorArgMap;

    public ConstructorValue(TypeDefinition typeDefinition) {
        super(typeDefinition);
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("constructor_value");
        template.add("type", getTypeDefinition().getName());
        //TODO: Add args
        return template.render();
    }

    public ConstructorValue(TypeDefinition typeDefinition, TypeDefinitionValue ...constructorArgArray){
        super(typeDefinition);
        this.constructorArgsArray = constructorArgArray;

    }

    public ConstructorValue(TypeDefinition typeDefinition, List<TypeDefinitionValue> constructorArgsList){
        super(typeDefinition);
        this.constructorArgsArray = constructorArgsList.toArray(new TypeDefinitionValue[constructorArgsList.size()]);
    }

    public ConstructorValue(TypeDefinition typeDefinition, Map<String, TypeDefinitionValue> constructorArgMap){
        super(typeDefinition);
        this.constructorArgMap = constructorArgMap;
    }



}
