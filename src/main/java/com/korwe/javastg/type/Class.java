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

    public ClassType getSuperClass() {
        return superClass;
    }

    public void setSuperClass(ClassType superClass) {
        this.superClass = superClass;
    }
}
