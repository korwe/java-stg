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
        addImportFor(imports, classDef, classDef.getSuperClass());

        //Check annotations
        for(AnnotationInstance annotationInstance : classDef.getAnnotations()){
            addImportFor(imports, classDef, annotationInstance.getAnnotation());
        }

        //Check interfaces
        for(Interface interfaceDef : classDef.getInterfaces()){
            addImportFor(imports, classDef, interfaceDef);
        }

        //Check attributes
        addImportsForIDDeclarations(imports, classDef, classDef.getAttributes());

        //Check all methods
        addImportsForMethods(imports, classDef, classDef.getMethods());
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
                addImportFor(imports, referenceType, method.getReturnType());
            }

            //Check Parameters
            addImportsForIDDeclarations(imports, referenceType, method.getParameters());

            //Check Annotations
            for(AnnotationInstance annotationInstance : method.getAnnotations()){
                addImportFor(imports, referenceType, annotationInstance.getAnnotation());
            }
        }

    }

    public static List<String> importsForIDDeclarations(ReferenceType referenceType, List<? extends IDDeclaration> idDeclarations){
        List<String> imports = new ArrayList<>();
        addImportsForIDDeclarations(imports, referenceType, idDeclarations);
        return imports;
    }

    public static void addImportsForIDDeclarations(List<String> imports, ReferenceType referenceType, List<? extends IDDeclaration> idDeclarations){
        for (IDDeclaration idDeclaration : idDeclarations) {
            addImportFor(imports, referenceType, idDeclaration.getType());

            //Check Annotations
            for(AnnotationInstance annotationInstance : idDeclaration.getAnnotations()){
                addImportFor(imports, referenceType, annotationInstance.getAnnotation());
            }
        }
    }

    public static void addImportFor(List<String> imports, ReferenceType referenceType, TypeDefinition typeDefinition) {
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
