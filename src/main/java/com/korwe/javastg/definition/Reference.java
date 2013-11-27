package com.korwe.javastg.definition;

import com.korwe.javastg.type.*;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Reference extends TypeDefinition implements ReferenceType {
    private final String packageName;

    public Reference(String packageName, String name) {
        super(name);
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean packageEqual(Reference referenceType){
        if(referenceType == null) return false;
        if(this.packageName == null && referenceType.packageName == null) return true;
        if(this.packageName != null && this.packageName.equals(referenceType.packageName)) return true;
        return false;
    }

    public boolean isBoxableType(){
        return Boxable.class.isAssignableFrom(this.getClass());
    }

    public boolean isClassType(){
        return ClassType.class.isAssignableFrom(this.getClass());
    }

    public boolean isEnumType(){
        return Enum.class.isAssignableFrom(this.getClass());
    }

    public boolean isInterfaceType(){
        return InterfaceType.class.isAssignableFrom(this.getClass());
    }

    public boolean isParameterized(){
        return ParameterizedType.class.isAssignableFrom(getClass());
    }

    @Override
    public boolean hasLiteralSupport(){
        return isBoxableType();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        Reference referenceType = (Reference)o;
        return packageEqual(referenceType) && getName().equals(referenceType.getName());
    }
}
