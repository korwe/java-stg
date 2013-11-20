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

        //Check attributes
        addImportsForAttributes(imports, classDef, classDef.getAttributes());

        //Check all methods
        addImportsForMethods(imports, classDef, classDef.getMethods());
    }

    public static List<String> importsForMethods(ReferenceType referenceType, List<Method> methods){
        List<String> imports = new ArrayList<>();
        addImportsForMethods(imports, referenceType, methods);
        return imports;

    }

    public static void addImportsForMethods(List<String> imports, ReferenceType referenceType, List<Method> methods){
        for(Method method : methods){
            //Check returnType
            if(method.getReturnType() != null && ReferenceType.class.isAssignableFrom(method.getReturnType().getClass())){
                addImportFor(imports, referenceType, method.getReturnType());
            }

            //Check Paramaters
            for(Attribute parameter : method.getParameters()){
                addImportFor(imports, referenceType, parameter.getType());
            }
        }

    }

    public static List<String> importsForAttributes(ReferenceType referenceType, List<Attribute> attributes){
        List<String> imports = new ArrayList<>();
        addImportsForAttributes(imports, referenceType, attributes);
        return imports;
    }

    public static void addImportsForAttributes(List<String> imports, ReferenceType referenceType, List<Attribute> attributes){
        for (Attribute attribute : attributes) {
                addImportFor(imports, referenceType, attribute.getType());
        }
    }

    public static void addImportFor(List<String> imports, ReferenceType referenceType, TypeDefinition typeDefinition) {
        if (ReferenceType.class.isAssignableFrom(typeDefinition.getClass())) {
            ReferenceType otherReferenceType = (ReferenceType) typeDefinition;
            if (referenceType != null && !referenceType.packageEqual(otherReferenceType)) {
                //Only add if not already contained
                if(!imports.contains(otherReferenceType.getPackageName())){
                    imports.add(otherReferenceType.getPackageName());
                }
            }
        }
    }
}
