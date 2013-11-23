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
        for(AnnotationInstance annotationInstance : classDef.getAnnotations()){
            addImportForTypeDefinition(imports, classDef, annotationInstance.getAnnotation());
        }

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

    public static List<String> importsForTypeParameters(ReferenceType referenceType, List<TypeParameter> typeParameters){
        List<String> imports = new ArrayList<>();
        addImportsForTypeParameters(imports, referenceType, typeParameters);
        return imports;
    }

    private static void addImportsForTypeParameters(List<String> imports, ReferenceType referenceType, List<TypeParameter> typeParameters) {
        for(TypeParameter typeParameter : typeParameters){
            addImportsForTypeDefinitions(imports, referenceType, typeParameter.getParentTypes());
        }
    }

    private static void addImportsForTypeDefinitions(List<String> imports, ReferenceType baseType, List<TypeDefinition> typeDefinitions){
        for(TypeDefinition typeDefinition : typeDefinitions){
            addImportForTypeDefinition(imports, baseType, typeDefinition);
        }
    }

    public static List<String> importsForMethods(ReferenceType referenceType, List<? extends Method> methods){
        List<String> imports = new ArrayList<>();
        addImportsForMethods(imports, referenceType, methods);
        return imports;

    }

    public static void addImportsForMethods(List<String> imports, ReferenceType referenceType, List<? extends Method> methods){
        for(Method method : methods){
            //Check returnType
            if(method.getReturnType() != null){
                addImportForTypeDefinition(imports, referenceType, method.getReturnType());
            }

            //Check Parameters
            addImportsForIDDeclarations(imports, referenceType, method.getParameters());

            //Check Annotations
            for(AnnotationInstance annotationInstance : method.getAnnotations()){
                addImportForTypeDefinition(imports, referenceType, annotationInstance.getAnnotation());
            }

            //Generics
            addImportsForTypeParameters(imports, referenceType, method.getTypeParameters());
        }

    }

    public static List<String> importsForIDDeclarations(ReferenceType referenceType, List<? extends IDDeclaration> idDeclarations){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclarations(imports, referenceType, idDeclarations);
        return imports;
    }

    public static void addImportsForIDDeclarations(List<String> imports, ReferenceType referenceType, List<? extends IDDeclaration> idDeclarations){
        for (IDDeclaration idDeclaration : idDeclarations) {
            addImportForTypeDefinition(imports, referenceType, idDeclaration.getType());

            //Check Annotations
            for(AnnotationInstance annotationInstance : idDeclaration.getAnnotations()){
                addImportForTypeDefinition(imports, referenceType, annotationInstance.getAnnotation());
            }
        }
    }

    public static void addImportForTypeDefinition(List<String> imports, ReferenceType referenceType, TypeDefinition typeDefinition) {
        if (typeDefinition != null && ReferenceType.class.isAssignableFrom(typeDefinition.getClass())) {
            ReferenceType otherReferenceType = (ReferenceType) typeDefinition;
            if (referenceType != null && !referenceType.packageEqual(otherReferenceType)) {
                //Only add if not already contained
                String importName = otherReferenceType.getPackageName()+"."+otherReferenceType.getName();
                if(!imports.contains(importName)){
                    imports.add(importName);
                }
            }
        }
    }
}
