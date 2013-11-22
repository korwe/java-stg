package com.korwe.javastg.value;

import com.korwe.javastg.exception.NoConstructorFoundException;
import com.korwe.javastg.exception.UnsupportedTypeException;
import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;
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
        checkTypeSupport();
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("constructor_value");
        template.add("type", getTypeDefinition().getName());
        template.add("args", constructorArguments());
        return template.render();
    }

    public ConstructorValue(TypeDefinition typeDefinition, TypeDefinitionValue ...constructorArgArray){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgsArray = constructorArgArray;

    }

    public ConstructorValue(TypeDefinition typeDefinition, List<TypeDefinitionValue> constructorArgsList){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgsArray = constructorArgsList.toArray(new TypeDefinitionValue[constructorArgsList.size()]);
    }

    public ConstructorValue(TypeDefinition typeDefinition, Map<String, TypeDefinitionValue> constructorArgMap){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgMap = constructorArgMap;
    }

    private TypeDefinitionValue[] constructorArguments(){

        Class classDef = (Class)getTypeDefinition();
        if(constructorArgsArray != null){
            ConstructorMethod constructorMethod = classDef.constructorForArguments(constructorArgsArray);
            return constructorArgsArray;
        }
        else if(constructorArgMap != null){
            ConstructorMethod constructorMethod = classDef.constructorForArguments(constructorArgMap);
            List<Parameter> parameters = constructorMethod.getParameters();
            TypeDefinitionValue[] arguments = new TypeDefinitionValue[parameters.size()];
            for(int i = 0 ; i < parameters.size(); i++){
                arguments[i] = constructorArgMap.get(parameters.get(i).getName());
            }
            return arguments;
        }
        else{
            //Check for default constructor
            if(classDef.getDefaultConstructor() == null){
                throw new NoConstructorFoundException();
            }
        }

        //Use default constructor
        return null;
    }

    private void checkTypeSupport(){
        if(!com.korwe.javastg.type.Class.class.isAssignableFrom(getTypeDefinition().getClass())){
            throw new UnsupportedTypeException();
        }
    }


}
