package com.korwe.javastg.util;

import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ImportUtil {

    public static List<String> importsForClass(Class classDef){
        List<String> imports = new ArrayList<>();
        addImportsForClass(imports, classDef);
        return imports;
    }

    public static void addImportsForClass(List<String> imports, Class classDef){

        //Check super class
        addImportForTypeDefinition(imports, classDef, classDef.getSuperClass());

        //Check annotations
        addImportsForAnnotatable(imports, classDef, classDef);

        //Check interfaces
        for(Interface interfaceDef : classDef.getInterfaces()){
            addImportForTypeDefinition(imports, classDef, interfaceDef);
        }

        //Check attributes
        addImportsForIDDeclarations(imports, classDef, classDef.getAttributes());

        //Check all methods
        addImportsForMethods(imports, classDef, classDef.getMethods());

        //Generics
        addImportsForTypeParameters(imports, classDef, classDef.getTypeParameters());
    }

    public static List<String> importsForMethods(ReferenceType referenceType, List<? extends Method> methods){
        List<String> imports = new ArrayList<>();
        addImportsForMethods(imports, referenceType, methods);
        return imports;

    }

    public static void addImportsForMethods(List<String> imports, ReferenceType baseType, List<? extends Method> methods){
        for(Method method : methods){
            addImportsForMethod(imports, baseType, method);
        }
    }

    public static List<String> importsForMethod(ReferenceType baseType, Method method){
        List<String> imports = new ArrayList<>();
        addImportsForMethod(imports, baseType, method);
        return imports;
    }

    public static void addImportsForMethod(List<String> imports, ReferenceType baseType, Method method){
        //Check returnType
        if(method.getReturnType() != null){
            addImportForTypeDefinition(imports, baseType, method.getReturnType());
        }

        //Check Parameters
        addImportsForIDDeclarations(imports, baseType, method.getParameters());

        //Check Annotations
        addImportsForAnnotatable(imports, baseType, method);

        //Check Type Parameters
        addImportsForTypeParameters(imports, baseType, method.getTypeParameters());
    }

    public static List<String> importsForParameterizedTypes(ReferenceType baseType, List<ParameterizedType> parameterizedTypes){
        List<String> imports = new ArrayList<>();
        addImportsForParameterizedTypes(imports, baseType, parameterizedTypes);
        return imports;
    }

    public static void addImportsForParameterizedTypes(List<String> imports, ReferenceType baseType, List<ParameterizedType> parameterizedTypes){
        for(ParameterizedType parameterizedType : parameterizedTypes){
            addImportsForParameterizedType(imports, baseType, parameterizedType);
        }
    }

    public static List<String> importsForParameterizedType(ReferenceType baseType, ParameterizedType parameterizedType){
        List<String> imports = new ArrayList<>();
        addImportsForParameterizedType(imports, baseType, parameterizedType);
        return imports;
    }

    public static void addImportsForParameterizedType(List<String> imports, ReferenceType baseType, ParameterizedType parameterizedType){
        if(ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass())){
            ReferenceType otherReferenceType = (ReferenceType)parameterizedType.getGenerifiable();
            if(otherReferenceType.getPackageName()!=null && !baseType.packageEqual(otherReferenceType)){
                //Only add if not already contained
                String importString = getImportString(otherReferenceType);
                if(!imports.contains(importString)) {
                    imports.add(importString);
                }


            }
        }

        addImportsForTypeDefinitions(imports, baseType, parameterizedType.getParameterTypes());
    }

    private static String getImportString(ReferenceType otherReferenceType) {
        return String.format("%s.%s", otherReferenceType.getPackageName(), otherReferenceType.getName());
    }


    public static List<String> importsForTypeParameters(ReferenceType baseType, List<TypeParameter> typeParameters){
        List<String> imports = new ArrayList<>();
        addImportsForTypeParameters(imports, baseType, typeParameters);
        return imports;
    }

    public static void addImportsForTypeParameters(List<String> imports, ReferenceType baseType, List<TypeParameter> typeParameters) {
        for(TypeParameter typeParameter : typeParameters){
            addImportsForTypeParameter(imports, baseType, typeParameter);
        }
    }

    public static List<String> importsForTypeParameter(ReferenceType baseType, TypeParameter typeParameter) {
        List<String> imports = new ArrayList<>();
        addImportsForTypeDefinitions(imports, baseType, typeParameter.getParentTypes());
        return imports;
    }

    public static void addImportsForTypeParameter(List<String> imports, ReferenceType baseType, TypeParameter typeParameter) {
        addImportsForTypeDefinitions(imports, baseType, typeParameter.getParentTypes());
    }

    public static List<String> importsForIDDeclarations(ReferenceType baseType, List<? extends IDDeclaration> idDeclarations){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclarations(imports, baseType, idDeclarations);
        return imports;
    }

    public static void addImportsForIDDeclarations(List<String> imports, ReferenceType baseType, List<? extends IDDeclaration> idDeclarations){
        for (IDDeclaration idDeclaration : idDeclarations) {
            addImportsForIDDeclaration(imports, baseType, idDeclaration);
        }
    }

    public static List<String> importsForIDDeclaration(ReferenceType baseType, IDDeclaration idDeclaration){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclaration(imports, baseType, idDeclaration);
        return imports;
    }

    public static void addImportsForIDDeclaration(List<String> imports, ReferenceType baseType, IDDeclaration idDeclaration){
        addImportForTypeDefinition(imports, baseType, idDeclaration.getType());

        //Check Annotations
        addImportsForAnnotatable(imports, baseType, idDeclaration);
    }

    public static List<String> importsForAnnotatables(ReferenceType baseType, List<? extends Annotatable> annotatables){
        List<String> imports = new ArrayList<>();
        addImportsForAnnotatables(imports, baseType, annotatables);
        return imports;
    }

    public static void addImportsForAnnotatables(List<String> imports, ReferenceType baseType, List<? extends Annotatable> annotatables){
        for(Annotatable annotatable : annotatables){
            addImportsForAnnotatable(imports, baseType, annotatable);
        }
    }

    public static List<String> importsForAnnotatable(ReferenceType baseType, Annotatable annotatable){
        List<String> imports = new ArrayList<>();
        addImportsForAnnotatable(imports, baseType, annotatable);
        return imports;
    }

    public static void addImportsForAnnotatable(List<String> imports, ReferenceType baseType, Annotatable annotatable){
        for(AnnotationInstance annotationInstance : annotatable.getAnnotations()){
            addImportForTypeDefinition(imports, baseType, annotationInstance.getAnnotation());
        }
    }

    public static List<String> importsForTypeDefinitions(ReferenceType baseType, List<? extends TypeDefinition> typeDefinitions){
        ArrayList<String> imports = new ArrayList<>();
        for(TypeDefinition typeDefinition : typeDefinitions){
            addImportForTypeDefinition(imports, baseType, typeDefinition);
        }
        return imports;
    }

    public static void addImportsForTypeDefinitions(List<String> imports, ReferenceType baseType, List<? extends TypeDefinition> typeDefinitions){
        for(TypeDefinition typeDefinition : typeDefinitions){
            addImportForTypeDefinition(imports, baseType, typeDefinition);
        }
    }

    public static void addImportForTypeDefinition(List<String> imports, ReferenceType referenceType, TypeDefinition typeDefinition) {
        //Only add if not already contained
        List<String> typeImports = importForTypeDefinition(referenceType, typeDefinition);
        for(String importString : typeImports){
            if(!imports.contains(importString)){
                imports.add(importString);
            }
        }
    }

    public static List<String> importForTypeDefinition(ReferenceType referenceType, TypeDefinition typeDefinition) {
        List<String> imports = new ArrayList<>();
        if (typeDefinition != null && ReferenceType.class.isAssignableFrom(typeDefinition.getClass())) {
            ReferenceType otherReferenceType = (ReferenceType) typeDefinition;
            if (referenceType != null) {
                if(otherReferenceType.getPackageName()!=null && !referenceType.packageEqual(otherReferenceType)){
                    String importString = getImportString(otherReferenceType);
                    //Only add if not already contained
                    if(!imports.contains(importString))
                    imports.add(importString);


                }

                if(otherReferenceType.isParameterized()){
                    addImportsForParameterizedType(imports, referenceType, (ParameterizedType)otherReferenceType);
                }
            }
        }
        return imports;
    }


}
