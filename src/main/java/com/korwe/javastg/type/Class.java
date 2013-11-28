package com.korwe.javastg.type;
/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Class extends MemberContainer implements ClassType{
    private ClassType superClass;

    public Class(String name) {
        super(name);
    }

    public Class(String packageName, String name) {
        super(packageName, name);
    }

    public Class(String packageName, String name, ClassType superClass) {
        super(packageName, name);
        this.superClass = superClass;
    }

    @Override
    public ClassType getSuperClass() {
        return superClass;
    }

    @Override
    public void setSuperClass(ClassType superClass) {
        this.superClass = superClass;
    }

    @Override
    public boolean canAssign(Type type){
        return ClassType.class.isAssignableFrom(type.getClass()) && ((ClassType)type).extendsFrom(this);
    }

    @Override
    public boolean extendsFrom(ClassType classType){
        if(this.equals(classType))return true;

        if(this.getSuperClass() != null){
            return classType.extendsFrom(this.getSuperClass());
        }

        return false;
    }
}
