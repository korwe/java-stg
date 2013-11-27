package com.korwe.javastg.value;

import com.korwe.javastg.definition.ConstructorMethod;
import com.korwe.javastg.definition.Parameter;
import com.korwe.javastg.type.TypeDefinition;
import com.korwe.javastg.exception.NoConstructorFoundException;
import com.korwe.javastg.exception.UnsupportedTypeException;
import com.korwe.javastg.type.MemberContainer;
import com.korwe.javastg.util.TemplateUtil;
import org.stringtemplate.v4.ST;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ConstructorValue extends TypeValue {

    private TypeValue[] constructorArgsArray;
    private Map<String, TypeValue> constructorArgMap;

    public ConstructorValue(TypeDefinition typeDefinition) {
        super(typeDefinition);
        checkTypeSupport();
    }

    @Override
    public String getCodeString() {
        ST template = TemplateUtil.template("constructor_value");
        template.add("type", getType().getName());
        template.add("args", constructorArguments());
        return template.render();
    }

    public ConstructorValue(TypeDefinition typeDefinition, TypeValue...constructorArgArray){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgsArray = constructorArgArray;

    }

    public ConstructorValue(TypeDefinition typeDefinition, List<TypeValue> constructorArgsList){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgsArray = constructorArgsList.toArray(new TypeValue[constructorArgsList.size()]);
    }

    public ConstructorValue(TypeDefinition typeDefinition, Map<String, TypeValue> constructorArgMap){
        super(typeDefinition);
        checkTypeSupport();
        this.constructorArgMap = constructorArgMap;
    }

    protected TypeValue[] constructorArguments(){

        MemberContainer classDef = (MemberContainer) getType();
        if(constructorArgsArray != null){
            ConstructorMethod constructorMethod = classDef.constructorForArguments(constructorArgsArray);
            return constructorArgsArray;
        }
        else if(constructorArgMap != null){
            ConstructorMethod constructorMethod = classDef.constructorForArguments(constructorArgMap);
            List<Parameter> parameters = constructorMethod.getParameters();
            TypeValue[] arguments = new TypeValue[parameters.size()];
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
        if(!MemberContainer.class.isAssignableFrom(getType().getClass())){
            throw new UnsupportedTypeException();
        }
    }


}
