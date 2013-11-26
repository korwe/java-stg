package com.korwe.javastg.test.util;

import com.korwe.javastg.type.*;
import com.korwe.javastg.util.ImportUtil;
import com.korwe.javastg.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TestImportUtil {

    @Test
    public void importForTypeDefinition(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        ReferenceType importedType = new ReferenceType("second.package", "Super");

        List<String> imports = ImportUtil.importForTypeDefinition(base, importedType);

        for(String importString : imports){
            assertEquals("Import created incorrectly", String.format("%s.%s", importedType.getPackageName(), importedType.getName()), importString);
        }


        //Should be null for primitives
        assertTrue("Import should be null for Primitive", ImportUtil.importForTypeDefinition(base, PrimitiveType.Int).isEmpty());

        //Should be null for unpackaged references
        ReferenceType referenceTypeWithNoPackage = new ReferenceType(null, "MyNonPackagedRefernceType");
        assertTrue("Import should be null for ReferenceType with no package", ImportUtil.importForTypeDefinition(base, referenceTypeWithNoPackage).isEmpty());

        //Should be null for reference in same package
        ReferenceType referenceTypeWithSamePackage = new ReferenceType(base.getPackageName(), "MyNonPackagedRefernceType");
        assertTrue("Import should be null for ReferenceType in same package", ImportUtil.importForTypeDefinition(base, referenceTypeWithSamePackage).isEmpty());



    }

    @Test
    public void addImportForTypeDefinition(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        ReferenceType importedType = new ReferenceType("second.package", "Super");
        List<String> imports = new ArrayList<>();
        ImportUtil.addImportForTypeDefinition(imports, base, importedType);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertFalse("Too many imports added", imports.size() > 1);
        assertEquals("Import added incorrectly", String.format("%s.%s",importedType.getPackageName(),importedType.getName()), imports.get(0));

        //No duplicate imports
        ImportUtil.addImportForTypeDefinition(imports, base, importedType);
        assertFalse("Should not add duplicate imports", imports.size() > 1);

        //Should not add null imports
        ImportUtil.addImportForTypeDefinition(imports, base, PrimitiveType.Double);
        assertFalse("Should not add null imports", imports.size() > 1);
    }

    @Test
    public void addImportsForTypeDefinitions(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        ReferenceType typeWithSecondPackage = new ReferenceType("second.package", "Super");
        ReferenceType typeWithThirdPackage = new ReferenceType("third.package", "Super");
        List<ReferenceType> types = new ArrayList<>();
        types.add(typeWithSecondPackage);
        types.add(typeWithThirdPackage);

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForTypeDefinitions(imports, base, types);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", types.size(), imports.size());
        for(ReferenceType type : types){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", type.getPackageName(), type.getName())));
        }

        //importsForTypeDefinitions (wrapper method)
        imports = ImportUtil.importsForTypeDefinitions(base, types);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", types.size(), imports.size());
        for(ReferenceType type : types){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", type.getPackageName(), type.getName())));
        }
    }

    @Test
    public void addImportsForAnnotatable(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        AnnotationInstance annotationInstance = new AnnotationInstance(TestUtil.getBasicAnnotation());
        base.addAnnotation(annotationInstance);


        assertFalse("No annotations added", base.getAnnotations().isEmpty());


        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForAnnotatable(imports, base, base);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", base.getAnnotations().size(), imports.size());
        for(AnnotationInstance a : base.getAnnotations()){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", a.getAnnotation().getPackageName(), a.getAnnotation().getName())));
        }

        //importsForAnnotatables (wrapper method)

        imports = ImportUtil.importsForAnnotatable(base, base);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", base.getAnnotations().size(), imports.size());
        for(AnnotationInstance a : base.getAnnotations()){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", a.getAnnotation().getPackageName(), a.getAnnotation().getName())));
        }

        //addImportsForAnnotatables() (multiple annotatables)

        List<Annotatable> annotatables = new ArrayList<>();
        Annotatable secondAnnotatable = TestUtil.getBasicEnum();
        secondAnnotatable.addAnnotation(new AnnotationInstance(new Annotation("annotations.place", "SecondAnnotation")));

        assertFalse("No annotations added to second annotatable", base.getAnnotations().isEmpty());

        annotatables.add(base);
        annotatables.add(secondAnnotatable);

        imports = new ArrayList<>();
        ImportUtil.addImportsForAnnotatables(imports, base, annotatables);

        assertFalse("Nothing was added to imports array", imports.isEmpty());

        List<Annotation> uniqueAnnotations = new ArrayList<>();
        for(Annotatable annotatable : annotatables){
            for(AnnotationInstance annotation : annotatable.getAnnotations()){
                assertFalse("Annotations are required to be unique", uniqueAnnotations.contains(annotation.getAnnotation()));
                uniqueAnnotations.add(annotation.getAnnotation());
            }
        }


        assertEquals("Incorrect number of imports added", uniqueAnnotations.size(), imports.size());
        for(Annotation annotation : uniqueAnnotations){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", annotation.getPackageName(), annotation.getName())));
        }

        //importsForAnnotatables (wrapper method for addImportsForAnnotatables)
        imports = ImportUtil.importsForAnnotatables(base, annotatables);
        assertEquals("Incorrect number of imports added", uniqueAnnotations.size(), imports.size());
        for(Annotation annotation : uniqueAnnotations){
            assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", annotation.getPackageName(), annotation.getName())));
        }
    }

    @Test
    public void addImportsForIdDeclaration(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        IDDeclaration idDeclaration = new IDDeclaration(TestUtil.getBasicEnum(), "firstIdDeclaration");

        assertThat(idDeclaration.getType(), instanceOf(ReferenceType.class));

        ReferenceType idDeclarationReferenceType = (ReferenceType) idDeclaration.getType();
        assertFalse("Id declaration's type should not be in same package", base.getPackageName().equals(idDeclarationReferenceType.getPackageName()));

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForIDDeclaration(imports, base, idDeclaration);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", 1, imports.size());

        assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", idDeclarationReferenceType.getPackageName(), idDeclarationReferenceType.getName())));

        //With annotations
        IDDeclaration idDeclarationWithAnnotations = new IDDeclaration(new ReferenceType("referenceType.place", "SomeReferenceType"), "secondIdDeclaration");
        ReferenceType idDeclarationWithAnnotationsReferenceType = (ReferenceType) idDeclarationWithAnnotations.getType();

        AnnotationInstance annotationInstance = TestUtil.getBasicAnnotation().getInstance();
        idDeclarationWithAnnotations.addAnnotation(annotationInstance);

        //we need unique imports
        for(AnnotationInstance ai : idDeclarationWithAnnotations.getAnnotations()){
            assertFalse("Annotation type should not be same as idDeclaration's type", idDeclarationWithAnnotationsReferenceType.equals(ai.getAnnotation()));
            assertFalse("Annotation type should not be same as idDeclarationWithAnnotations's type", idDeclarationReferenceType.equals(ai.getAnnotation()));
        }


        imports = new ArrayList<>();
        ImportUtil.addImportsForIDDeclaration(imports, base, idDeclarationWithAnnotations);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", 1+idDeclarationWithAnnotations.getAnnotations().size(), imports.size());

        assertTrue("Import not added or added incorrectly for idDeclaration", imports.contains(String.format("%s.%s", idDeclarationWithAnnotationsReferenceType.getPackageName(), idDeclarationWithAnnotationsReferenceType.getName())));
        assertTrue("Import not added or added incorrectly for idDeclaration's annoation", imports.contains(String.format("%s.%s", annotationInstance.getAnnotation().getPackageName(), annotationInstance.getAnnotation().getName())));


        //importsForIDDeclaration (wrapper method for addImportsForIDDeclaration)
        imports = ImportUtil.importsForIDDeclaration(base, idDeclaration);

        assertFalse("Nothing was added to imports array", imports.isEmpty());
        assertEquals("Incorrect number of imports added", 1, imports.size());

        assertTrue("Import not added or added incorrectly", imports.contains(String.format("%s.%s", idDeclarationReferenceType.getPackageName(), idDeclarationReferenceType.getName())));

        //addImportsForIDDeclarations(wrapper method for multiple id declarations)
        //we need unique imports
        assertFalse("Id declaration's type should not be in same package", base.getPackageName().equals(idDeclarationWithAnnotationsReferenceType.getPackageName()));

        //we need unique imports
        assertFalse("Id declarations should have different types", idDeclarationReferenceType.equals(idDeclarationWithAnnotationsReferenceType));

        List<IDDeclaration> idDeclarations = new ArrayList<>();
        idDeclarations.add(idDeclaration);
        idDeclarations.add(idDeclarationWithAnnotations);

        List<ReferenceType> uniqueReferenceTypes = new ArrayList<>();

        uniqueReferenceTypes.add(idDeclarationReferenceType);
        uniqueReferenceTypes.add(idDeclarationWithAnnotationsReferenceType);
        for(AnnotationInstance ai : idDeclarationWithAnnotations.getAnnotations()){
            uniqueReferenceTypes.add(ai.getAnnotation());
        }

        imports = new ArrayList<>();
        ImportUtil.addImportsForIDDeclarations(imports, base, idDeclarations);

        assertEquals("Incorrect number of imports added", uniqueReferenceTypes.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferenceTypes){
            assertTrue(String.format("Import not added or added incorrectly for reference type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        //importsForIDDeclarations(wrapper method for addImportsForIDDeclarations)
        imports = ImportUtil.importsForIDDeclarations(base, idDeclarations);

        assertEquals("Incorrect number of imports added", uniqueReferenceTypes.size(), imports.size());

        for(ReferenceType referenceType : uniqueReferenceTypes){
            assertTrue(String.format("Import not added or added incorrectly for reference type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

    }

    @Test
    public void addImportsForTypeParameter(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        TypeParameter typeParameter = TestUtil.getBasicTypeParameterWithParent();

        assertTrue("Type parameter should have parentTypes", typeParameter.getParentTypes().size() > 0);

        //Package Uniqueness
        for(ReferenceType referenceType : typeParameter.getParentTypes()){
            assertNotEquals("Type parameter parents and base type should not be in same package", base.getPackageName(), referenceType.getPackageName());
        }

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForTypeParameter(imports, base, typeParameter);

        assertEquals("Incorrect number of imports added", typeParameter.getParentTypes().size(), imports.size());
        for(ReferenceType referenceType : typeParameter.getParentTypes()){
            assertTrue(String.format("Import not added or added incorrectly for parent type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        //importsForTypeParameter (wrapper for addImportsForTypeParameter)
        imports = ImportUtil.importsForTypeParameter(base, typeParameter);

        assertEquals("Incorrect number of imports added", typeParameter.getParentTypes().size(), imports.size());
        for(ReferenceType referenceType : typeParameter.getParentTypes()){
            assertTrue(String.format("Import not added or added incorrectly for parent type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }
    }

    @Test
    public void addImportsForTypeParameters(){
        ReferenceType base = TestUtil.getBasicConcreteClass();
        TypeParameter typeParameter1 = TestUtil.getBasicTypeParameterWithParent();
        TypeParameter typeParameter2 = TestUtil.getMultiBoundTypeParameter();

        assertTrue("Type parameter should have parentTypes", typeParameter1.getParentTypes().size() > 0);
        assertTrue("Type parameter2 should have parentTypes", typeParameter2.getParentTypes().size() > 0);

        List<TypeParameter> typeParameters = new ArrayList<>();
        typeParameters.add(typeParameter1);
        typeParameters.add(typeParameter2);


        List<ReferenceType> uniqueReferences = new ArrayList<>();
        //Uniqueness of classes/packages
        for(TypeParameter typeParameter : typeParameters){
            for(ReferenceType referenceType : typeParameter.getParentTypes()){
                assertNotEquals("Type parameter parents and base type should not be in same package", base.getPackageName(), referenceType.getPackageName());
                assertFalse("All type parameter parents should be unique", uniqueReferences.contains(referenceType));
                uniqueReferences.add(referenceType);
            }
        }

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForTypeParameters(imports, base, typeParameters);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parent type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        //importsForTypeParameters (wrapper for addImportsForTypeParameters)
        imports = ImportUtil.importsForTypeParameters(base, typeParameters);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parent type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }
    }


    @Test
    public void addImportsForParameterizedType(){
        ReferenceType baseType = TestUtil.getBasicConcreteClass();
        ParameterizedType parameterizedType = TestUtil.getBasicParameterizedType();
        //Make sure there are parameterTypes
        assertTrue("ParameterizedType does not have any parameterTypes", parameterizedType.getParameterTypes().size() > 0);

        //Uniqueness
        List<ReferenceType> uniqueReferences = new ArrayList<>();
        assertTrue(ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass()));
        uniqueReferences.add((ReferenceType)parameterizedType.getGenerifiable());
        for(ReferenceType parameterType : parameterizedType.getParameterTypes()){
            assertFalse("ParameterTypes should be unique", baseType.equals(parameterType));
            uniqueReferences.add(parameterType);
        }

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForParameterizedType(imports, baseType, parameterizedType);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parameter type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        //importsForParameterizedType (wrapper for addImportsForParamaterizedType)

        imports = ImportUtil.importsForParameterizedType(baseType, parameterizedType);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parameter type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }
    }

    @Test
    public void addImportsForParamterizedTypes(){
        ReferenceType baseType = TestUtil.getBasicConcreteClass();
        ParameterizedType parameterizedType1 = TestUtil.getBasicParameterizedType();
        ParameterizedType parameterizedType2 = TestUtil.getMultiBoundParameterizedType();

        //Make sure there are parameterTypes
        assertTrue("ParameterizedType does not have any parameterTypes", parameterizedType1.getParameterTypes().size() > 0);
        assertTrue("ParameterizedType does not have any parameterTypes", parameterizedType2.getParameterTypes().size() > 0);

        List<ParameterizedType> parameterizedTypes = new ArrayList<>();
        parameterizedTypes.add(parameterizedType1);
        parameterizedTypes.add(parameterizedType2);

        //Uniqueness
        List<ReferenceType> uniqueReferences = new ArrayList<>();
        for(ParameterizedType parameterizedType : parameterizedTypes){
            assertTrue("Generifiable must be a ReferenceType", ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass()));
            assertFalse("Generifiable ReferenceTypes should be unique", uniqueReferences.contains(parameterizedType.getGenerifiable()));
            uniqueReferences.add((ReferenceType)parameterizedType.getGenerifiable());
            for(ReferenceType parameterType : parameterizedType.getParameterTypes()){
                assertFalse("ParameterTypes should be unique", baseType.equals(parameterType));
                uniqueReferences.add(parameterType);
            }
        }

        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForParameterizedTypes(imports, baseType, parameterizedTypes);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parameter type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        imports = ImportUtil.importsForParameterizedTypes(baseType, parameterizedTypes);
        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parameter type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

    }

    @Test
    public void importsForTypeDefinition_Parameterized(){
        ReferenceType baseType = TestUtil.getBasicConcreteClass();
        ParameterizedType parameterizedType = TestUtil.getBasicParameterizedType();

        List<ReferenceType> uniqueReferences = new ArrayList<>();

        //uniqueness
        assertTrue("Generifiable must be a referenceType", ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass()));
        uniqueReferences.add((ReferenceType)parameterizedType.getGenerifiable());
        for(ReferenceType parameterType : parameterizedType.getParameterTypes()){
            assertFalse("ParameterTypes should be unique", baseType.equals(parameterType));
            uniqueReferences.add(parameterType);
        }

        List<String> imports = ImportUtil.importForTypeDefinition(baseType, parameterizedType);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for parameter type(%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

    }

    /**
     * This test checks only whether all components of the method are being checked for imports
     */
    @Test
    public void importsForMethod(){
        ReferenceType baseType = TestUtil.getBasicConcreteClass();
        Method method = TestUtil.getPrivateAnnotatedParameterizedConcreteMethodWithParameterizedParameter();

        List<ReferenceType> uniqueReferences = new ArrayList<>();

        assertTrue("Method is required to have at least 1 ReferenceType parameter and 1 ParameterizedType parameter", method.getParameters().size() > 1);
        boolean methodHasReferenceTypeParameter = false;
        boolean methodHasParameterizedParameter = false;
        for(Parameter parameter : method.getParameters()){
            if(parameter.getType().isReferenceType()){
                ReferenceType paramReferenceType = (ReferenceType)parameter.getType();
                assertFalse("ReferenceType Parameter shouldn't be same as baseType", baseType.equals(paramReferenceType));
                assertFalse("ReferenceType Parameter should be in different package from baseType", baseType.packageEqual(paramReferenceType));
                if((paramReferenceType).isParameterized()){
                    //ParameterizedType parameters
                    ParameterizedType parameterizedType = (ParameterizedType)paramReferenceType;
                    assertFalse("Parameterized Parameter's type should have at least 1 ParentType", parameterizedType.getParameterTypes().isEmpty());
                    for(ReferenceType referenceType : parameterizedType.getParameterTypes()){
                        assertFalse("Paramterized Parameter's parameterTypes' package should be different to baseType", baseType.packageEqual(referenceType));
                        assertFalse("All reference types should be unique", uniqueReferences.contains(referenceType));
                        uniqueReferences.add(referenceType);
                    }
                    assertTrue("Parameterized Parameter's generifiable should be a referenceType", ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass()));
                    ReferenceType generifiableReferece = (ReferenceType) parameterizedType.getGenerifiable();
                    assertFalse("Paramterized Parameter's type's package should be different to baseType", baseType.packageEqual(generifiableReferece));
                    assertFalse("All reference types should be unique", uniqueReferences.contains(generifiableReferece));
                    uniqueReferences.add(generifiableReferece);

                    methodHasParameterizedParameter = true;

                }
                else{
                    //ReferenceType parameters
                    assertFalse("ReferenceType parameter's type's package should be different from base package", baseType.packageEqual(paramReferenceType));
                    assertFalse("All reference types should be unique", uniqueReferences.contains(paramReferenceType));
                    uniqueReferences.add(paramReferenceType);
                    methodHasReferenceTypeParameter = true;
                }
            }
        }

        assertTrue("Method is required to have at least 1 ReferenceType parameter", methodHasReferenceTypeParameter);
        assertTrue("Method is required to have at least 1 ParameterizedType parameter", methodHasParameterizedParameter);

        //Annotations
        assertFalse("Method is required to have at least 1 annotation", method.getAnnotations().isEmpty());
        for(AnnotationInstance annotationInstance : method.getAnnotations()){
            assertFalse("Annotation's type's package should be different from base package", baseType.packageEqual(annotationInstance.getAnnotation()));
            assertFalse("All reference types should be unique", uniqueReferences.contains(annotationInstance.getAnnotation()));
            uniqueReferences.add(annotationInstance.getAnnotation());
        }


        //TypeParameters
        assertFalse("Method is required to have at least 1 TypeParameter", method.getTypeParameters().isEmpty());
        for(TypeParameter typeParameter : method.getTypeParameters()){
            for(ReferenceType referenceType : typeParameter.getParentTypes()){
                assertFalse("TypeParameter's parents' type's package should be different from base package", baseType.packageEqual(referenceType));
                assertFalse("All reference types should be unique", uniqueReferences.contains(referenceType));
                uniqueReferences.add(referenceType);
            }
        }

        //ReturnType
        assertNotNull("Method is required to have a returnType", method.getReturnType());
        assertTrue("Method's returnType is required to be a ReferenceType", method.getReturnType().isReferenceType());
        assertFalse("Method's returnType type is required to be in a different package from the base package", baseType.packageEqual((ReferenceType) method.getReturnType()));
        assertFalse("All reference types should be unique", uniqueReferences.contains(method.getReturnType()));
        uniqueReferences.add((ReferenceType)method.getReturnType());



        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForMethod(imports, baseType, method);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for (%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        imports = ImportUtil.importsForMethod(baseType, method);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for (%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

    }

    /**
     * This test checks only whether all components of the methods are being checked for imports (wrapper methods for importsForMethod)
     */
    @Test
    public void importsForMethods(){
        ReferenceType baseType = TestUtil.getBasicConcreteClass();
        List<Method> methods = new ArrayList<>();
        methods.add(TestUtil.getPrivateAnnotatedParameterizedConcreteMethodWithParameterizedParameter());
        methods.add(TestUtil.getPrivateAnnotatedParameterizedAbstractMethodWithParameterizedParameter());


        List<ReferenceType> uniqueReferences = new ArrayList<>();

        for(Method method : methods){
            assertTrue("Method is required to have at least 1 ReferenceType parameter and 1 ParameterizedType parameter", method.getParameters().size() > 1);
            boolean methodHasReferenceTypeParameter = false;
            boolean methodHasParameterizedParameter = false;
            for(Parameter parameter : method.getParameters()){
                if(parameter.getType().isReferenceType()){
                    ReferenceType paramReferenceType = (ReferenceType)parameter.getType();
                    assertFalse("ReferenceType Parameter shouldn't be same as baseType", baseType.equals(paramReferenceType));
                    assertFalse("ReferenceType Parameter should be in different package from baseType", baseType.packageEqual(paramReferenceType));
                    if((paramReferenceType).isParameterized()){
                        //ParameterizedType parameters
                        ParameterizedType parameterizedType = (ParameterizedType)paramReferenceType;
                        assertFalse("Parameterized Parameter's type should have at least 1 ParentType", parameterizedType.getParameterTypes().isEmpty());
                        for(ReferenceType referenceType : parameterizedType.getParameterTypes()){
                            assertFalse("Paramterized Parameter's parameterTypes' package should be different to baseType", baseType.packageEqual(referenceType));
                            assertFalse("All reference types should be unique", uniqueReferences.contains(referenceType));
                            uniqueReferences.add(referenceType);
                        }
                        assertTrue("Parameterized Parameter's generifiable should be a referenceType", ReferenceType.class.isAssignableFrom(parameterizedType.getGenerifiable().getClass()));
                        ReferenceType generifiableReferece = (ReferenceType) parameterizedType.getGenerifiable();
                        assertFalse("Paramterized Parameter's type's package should be different to baseType", baseType.packageEqual(generifiableReferece));
                        assertFalse("All reference types should be unique", uniqueReferences.contains(generifiableReferece));
                        uniqueReferences.add(generifiableReferece);

                        methodHasParameterizedParameter = true;

                    }
                    else{
                        //ReferenceType parameters
                        assertFalse("ReferenceType parameter's type's package should be different from base package", baseType.packageEqual(paramReferenceType));
                        assertFalse("All reference types should be unique", uniqueReferences.contains(paramReferenceType));
                        uniqueReferences.add(paramReferenceType);
                        methodHasReferenceTypeParameter = true;
                    }
                }
            }

            assertTrue("Method is required to have at least 1 ReferenceType parameter", methodHasReferenceTypeParameter);
            assertTrue("Method is required to have at least 1 ParameterizedType parameter", methodHasParameterizedParameter);

            //Annotations
            assertFalse("Method is required to have at least 1 annotation", method.getAnnotations().isEmpty());
            for(AnnotationInstance annotationInstance : method.getAnnotations()){
                assertFalse("Annotation's type's package should be different from base package", baseType.packageEqual(annotationInstance.getAnnotation()));
                assertFalse("All reference types should be unique", uniqueReferences.contains(annotationInstance.getAnnotation()));
                uniqueReferences.add(annotationInstance.getAnnotation());
            }


            //TypeParameters
            assertFalse("Method is required to have at least 1 TypeParameter", method.getTypeParameters().isEmpty());
            for(TypeParameter typeParameter : method.getTypeParameters()){
                for(ReferenceType referenceType : typeParameter.getParentTypes()){
                    assertFalse("TypeParameter's parents' type's package should be different from base package", baseType.packageEqual(referenceType));
                    assertFalse("All reference types should be unique", uniqueReferences.contains(referenceType));
                    uniqueReferences.add(referenceType);
                }
            }

            //ReturnType
            assertNotNull("Method is required to have a returnType", method.getReturnType());
            assertTrue("Method's returnType is required to be a ReferenceType", method.getReturnType().isReferenceType());
            assertFalse("Method's returnType type is required to be in a different package from the base package", baseType.packageEqual((ReferenceType) method.getReturnType()));
            assertFalse("All reference types should be unique", uniqueReferences.contains(method.getReturnType()));
            uniqueReferences.add((ReferenceType)method.getReturnType());

        }



        List<String> imports = new ArrayList<>();
        ImportUtil.addImportsForMethods(imports, baseType, methods);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for (%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

        imports = ImportUtil.importsForMethods(baseType, methods);

        assertEquals("Incorrect number of imports added", uniqueReferences.size(), imports.size());
        for(ReferenceType referenceType : uniqueReferences){
            assertTrue(String.format("Import not added or added incorrectly for (%s.%s)", referenceType.getPackageName(), referenceType.getName()), imports.contains(String.format("%s.%s", referenceType.getPackageName(), referenceType.getName())));
        }

    }
}
