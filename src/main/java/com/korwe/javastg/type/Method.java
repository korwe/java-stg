package com.korwe.javastg.type;

import com.korwe.javastg.value.TypeDefinitionValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Method extends Annotatable implements Generifiable{
    private String name;
    private AccessModifier accessModifier;
    private TypeDefinition returnType;
    private List<Parameter> parameters;
    private List<TypeParameter> typeParameters;
    private boolean isStatic;

    public Method(){
        init();
    }

    public Method(String name){
        this.name = name;
        init();
    }

    public Method(AccessModifier accessModifier, String name){
        this.accessModifier = accessModifier;
        this.name = name;
        init();
    }

    public Method(TypeDefinition returnType, String name){
        this.returnType = returnType;
        this.name = name;
        init();
    }

    public Method(AccessModifier accessModifier, TypeDefinition returnType, String name){
        this.accessModifier = accessModifier;
        this.returnType = returnType;
        this.name = name;
        init();
    }

    public Method(AccessModifier accessModifier, TypeDefinition returnType, String name, boolean isStatic){
        this.accessModifier = accessModifier;
        this.returnType = returnType;
        this.name = name;
        this.isStatic = isStatic;
        init();
    }

    private void init(){
        parameters = new ArrayList<>();
        this.typeParameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public TypeDefinition getReturnType() {
        return returnType;
    }

    public void setReturnType(TypeDefinition returnType) {
        this.returnType = returnType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void addParamater(Parameter parameter) {
        parameters.add(parameter);
    }

    public boolean isAbstract() {
        return AbstractMethod.class.isAssignableFrom(getClass());
    }

    public boolean isConcrete() {
        return ConcreteMethod.class.isAssignableFrom(getClass());
    }

    public boolean isConstructor(){
        return ConstructorMethod.class.isAssignableFrom(getClass());
    }

    public boolean supportsArguments(TypeDefinitionValue[] arguments){
        for(int i = 0; i < parameters.size(); i++){
            if(!parameters.get(i).getType().isCompatibleWith(arguments[i])){
                return false;
            }
        }
        return true;
    }

    public boolean supportsArguments(Map<String, TypeDefinitionValue> arguments){
        for(Parameter parameter : parameters){
            if(!arguments.containsKey(parameter.getName()) || !parameter.getType().isCompatibleWith(arguments.get(parameter.getName()))){
              return false;
            }
        }
        return true;
    }

    @Override
    public void setTypeParameters(List<TypeParameter> typeParameters){
        this.typeParameters = typeParameters;
    }

    @Override
    public void addTypeParameter(TypeParameter typeParameter){
        this.typeParameters.add(typeParameter);
    }

    @Override
    public List<TypeParameter> getTypeParameters(){
        return this.typeParameters;
    }
}
