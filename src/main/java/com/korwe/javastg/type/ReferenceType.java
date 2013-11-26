package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ReferenceType extends TypeDefinition{
    private final String packageName;

    public ReferenceType(String packageName, String name) {
        super(name);
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean packageEqual(ReferenceType referenceType){
        if(referenceType == null) return false;
        if(this.packageName == null && referenceType.packageName == null) return true;
        if(this.packageName != null && this.packageName.equals(referenceType.packageName)) return true;
        return false;
    }

    public boolean isBoxableType(){
        return BoxableType.class.isAssignableFrom(this.getClass());
    }

    public boolean isClassType(){
        return Class.class.isAssignableFrom(this.getClass());
    }

    public boolean isEnumType(){
        return Enum.class.isAssignableFrom(this.getClass());
    }

    public boolean isInterfaceType(){
        return Interface.class.isAssignableFrom(this.getClass());
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
        ReferenceType referenceType = (ReferenceType)o;
        return packageEqual(referenceType) && getName().equals(referenceType.getName());
    }
}
