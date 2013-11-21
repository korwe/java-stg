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
        if(classDef.getSuperClass()!=null && !classDef.packageEqual(classDef.getSuperClass())){
            imports.add(classDef.getSuperClass().getPackageName());
        }

        //Check interfaces
        for(Interface interfaceDef : classDef.getInterfaces()){
            addImportFor(imports, classDef, interfaceDef);
        }

        //Check attributes
        addImportsForAttributes(imports, classDef, classDef.getAttributes());

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
            for(Parameter parameter : method.getParameters()){
                addImportFor(imports, referenceType, parameter.getType());
            }
        }

    }

    public static List<String> importsForAttributes(ReferenceType referenceType, List<? extends IDDeclaration> attributes){
        List<String> imports = new ArrayList<>();
        addImportsForAttributes(imports, referenceType, attributes);
        return imports;
    }

    public static void addImportsForAttributes(List<String> imports, ReferenceType referenceType, List<? extends IDDeclaration> attributes){
        for (IDDeclaration attribute : attributes) {
                addImportFor(imports, referenceType, attribute.getType());
        }
    }

    public static void addImportFor(List<String> imports, ReferenceType referenceType, TypeDefinition typeDefinition) {
        if (ReferenceType.class.isAssignableFrom(typeDefinition.getClass())) {
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
