package com.korwe.javastg.util;

import com.korwe.javastg.definition.AnnotationInstance;
import com.korwe.javastg.definition.ParameterizedInterface;
import com.korwe.javastg.definition.Reference;
import com.korwe.javastg.definition.TypeParameter;
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
        if(classDef.getSuperClass() !=null){
            if(ParameterizedType.class.isAssignableFrom(classDef.getSuperClass().getClass())){
                addImportsForParameterizedType(imports, classDef, (ParameterizedType)classDef.getSuperClass());
            }
            else{
                addImportForType(imports, classDef, classDef.getSuperClass());
            }
        }

        //Check annotations
        addImportsForAnnotatable(imports, classDef, classDef);

        //Check interfaces
        for(InterfaceType interfaceType : classDef.getInterfaces()){
            if(ParameterizedInterface.class.isAssignableFrom(interfaceType.getClass())){
                addImportsForParameterizedType(imports, classDef, (ParameterizedInterface)interfaceType);
            }
            else {
                addImportForType(imports, classDef, interfaceType);
            }
        }

        //Check attributes
        addImportsForIDDeclarations(imports, classDef, classDef.getAttributes());

        //Check all methods
        addImportsForMethods(imports, classDef, classDef.getMethods());

        //TypeParameters
        addImportsForTypeParameters(imports, classDef, classDef.getTypeParameters());
    }

    public static List<String> importsForMethods(Reference referenceType, List<? extends Method> methods){
        List<String> imports = new ArrayList<>();
        addImportsForMethods(imports, referenceType, methods);
        return imports;

    }

    public static void addImportsForMethods(List<String> imports, Reference baseType, List<? extends Method> methods){
        for(Method method : methods){
            addImportsForMethod(imports, baseType, method);
        }
    }

    public static List<String> importsForMethod(Reference baseType, Method method){
        List<String> imports = new ArrayList<>();
        addImportsForMethod(imports, baseType, method);
        return imports;
    }

    public static void addImportsForMethod(List<String> imports, Reference baseType, Method method){
        //Check returnType
        if(method.getReturnType() != null){
            addImportForType(imports, baseType, method.getReturnType());
        }

        //Check Parameters
        addImportsForIDDeclarations(imports, baseType, method.getParameters());

        //Check Annotations
        addImportsForAnnotatable(imports, baseType, method);

        //Check Type Parameters
        addImportsForTypeParameters(imports, baseType, method.getTypeParameters());
    }

    public static List<String> importsForParameterizedTypes(Reference baseType, List<ParameterizedType> parameterizedTypes){
        List<String> imports = new ArrayList<>();
        addImportsForParameterizedTypes(imports, baseType, parameterizedTypes);
        return imports;
    }

    public static void addImportsForParameterizedTypes(List<String> imports, Reference baseType, List<ParameterizedType> parameterizedTypes){
        for(ParameterizedType parameterizedType : parameterizedTypes){
            addImportsForParameterizedType(imports, baseType, parameterizedType);
        }
    }

    public static List<String> importsForParameterizedType(Reference baseType, ParameterizedType parameterizedType){
        List<String> imports = new ArrayList<>();
        addImportsForParameterizedType(imports, baseType, parameterizedType);
        return imports;
    }

    public static void addImportsForParameterizedType(List<String> imports, Reference baseType, ParameterizedType parameterizedType){
        if(Reference.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass())){
            Reference otherReferenceType = (Reference)parameterizedType.getGenerifiable();
            if(otherReferenceType.getPackageName()!=null && !baseType.packageEqual(otherReferenceType)){
                //Only add if not already contained
                String importString = getImportString(otherReferenceType);
                if(!imports.contains(importString)) {
                    imports.add(importString);
                }


            }
        }

        addImportsForTypes(imports, baseType, parameterizedType.getParameterTypes());
    }

    private static String getImportString(Reference otherReferenceType) {
        return String.format("%s.%s", otherReferenceType.getPackageName(), otherReferenceType.getName());
    }


    public static List<String> importsForTypeParameters(Reference baseType, List<TypeParameter> typeParameters){
        List<String> imports = new ArrayList<>();
        addImportsForTypeParameters(imports, baseType, typeParameters);
        return imports;
    }

    public static void addImportsForTypeParameters(List<String> imports, Reference baseType, List<TypeParameter> typeParameters) {
        for(TypeParameter typeParameter : typeParameters){
            addImportsForTypeParameter(imports, baseType, typeParameter);
        }
    }

    public static List<String> importsForTypeParameter(Reference baseType, TypeParameter typeParameter) {
        List<String> imports = new ArrayList<>();
        addImportsForTypes(imports, baseType, typeParameter.getParentTypes());
        return imports;
    }

    public static void addImportsForTypeParameter(List<String> imports, Reference baseType, TypeParameter typeParameter) {
        addImportsForTypes(imports, baseType, typeParameter.getParentTypes());
    }

    public static List<String> importsForIDDeclarations(Reference baseType, List<? extends IDDeclaration> idDeclarations){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclarations(imports, baseType, idDeclarations);
        return imports;
    }

    public static void addImportsForIDDeclarations(List<String> imports, Reference baseType, List<? extends IDDeclaration> idDeclarations){
        for (IDDeclaration idDeclaration : idDeclarations) {
            addImportsForIDDeclaration(imports, baseType, idDeclaration);
        }
    }

    public static List<String> importsForIDDeclaration(Reference baseType, IDDeclaration idDeclaration){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclaration(imports, baseType, idDeclaration);
        return imports;
    }

    public static void addImportsForIDDeclaration(List<String> imports, Reference baseType, IDDeclaration idDeclaration){
        addImportForType(imports, baseType, idDeclaration.getType());

        //Check Annotations
        addImportsForAnnotatable(imports, baseType, idDeclaration);
    }

    public static List<String> importsForAnnotatables(Reference baseType, List<? extends Annotatable> annotatables){
        List<String> imports = new ArrayList<>();
        addImportsForAnnotatables(imports, baseType, annotatables);
        return imports;
    }

    public static void addImportsForAnnotatables(List<String> imports, Reference baseType, List<? extends Annotatable> annotatables){
        for(Annotatable annotatable : annotatables){
            addImportsForAnnotatable(imports, baseType, annotatable);
        }
    }

    public static List<String> importsForAnnotatable(Reference baseType, Annotatable annotatable){
        List<String> imports = new ArrayList<>();
        addImportsForAnnotatable(imports, baseType, annotatable);
        return imports;
    }

    public static void addImportsForAnnotatable(List<String> imports, Reference baseType, Annotatable annotatable){
        for(AnnotationInstance annotationInstance : annotatable.getAnnotations()){
            addImportForType(imports, baseType, annotationInstance.getAnnotation());
        }
    }

    public static List<String> importsForTypes(Reference baseType, List<? extends Type> types){
        ArrayList<String> imports = new ArrayList<>();
        for(Type type : types){
            addImportForType(imports, baseType, type);
        }
        return imports;
    }

    public static void addImportsForTypes(List<String> imports, Reference baseType, List<? extends Type> types){
        for(Type type : types){
            addImportForType(imports, baseType, type);
        }
    }

    public static void addImportForType(List<String> imports, Reference baseType, Type type) {
        //Only add if not already contained
        List<String> typeImports = importForType(baseType, type);
        for(String importString : typeImports){
            if(!imports.contains(importString)){
                imports.add(importString);
            }
        }
    }

    public static List<String> importForType(Reference baseType, Type type) {
        List<String> imports = new ArrayList<>();
        if (type != null && Reference.class.isAssignableFrom(type.getClass())) {
            Reference otherReferenceType = (Reference) type;
            if (baseType != null) {
                if(otherReferenceType.getPackageName()!=null && !baseType.packageEqual(otherReferenceType)){
                    String importString = getImportString(otherReferenceType);
                    //Only add if not already contained
                    if(!imports.contains(importString))
                    imports.add(importString);


                }

                if(otherReferenceType.isParameterized()){
                    addImportsForParameterizedType(imports, baseType, (ParameterizedType)otherReferenceType);
                }
            }
        }
        return imports;
    }


}
