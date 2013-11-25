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

        String importString = ImportUtil.importForTypeDefinition(base, importedType);

        assertEquals("Import created incorrectly", String.format("%s.%s",importedType.getPackageName(),importedType.getName()), importString);


        //Should be null for primitives
        assertNull("Import should be null for Primitive", ImportUtil.importForTypeDefinition(base, PrimitiveType.Int));

        //Should be null for unpackaged references
        ReferenceType referenceTypeWithNoPackage = new ReferenceType(null, "MyNonPackagedRefernceType");
        assertNull("Import should be null for ReferenceType with no package", ImportUtil.importForTypeDefinition(base, referenceTypeWithNoPackage));

        //Should be null for reference in same package
        ReferenceType referenceTypeWithSamePackage = new ReferenceType(base.getPackageName(), "MyNonPackagedRefernceType");
        assertNull("Import should be null for ReferenceType in same package", ImportUtil.importForTypeDefinition(base, referenceTypeWithSamePackage));



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


}
