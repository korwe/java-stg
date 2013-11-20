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
}
