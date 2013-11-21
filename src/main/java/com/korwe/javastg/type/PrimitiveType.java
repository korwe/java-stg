package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class PrimitiveType extends TypeDefinition{
    public static final PrimitiveType BOOLEAN = new PrimitiveType("boolean");
    public static final PrimitiveType SHORT = new PrimitiveType("short");
    public static final PrimitiveType INT = new PrimitiveType("int");
    public static final PrimitiveType LONG = new PrimitiveType("long");
    public static final PrimitiveType FLOAT = new PrimitiveType("float");
    public static final PrimitiveType DOUBLE = new PrimitiveType("double");
    public static final PrimitiveType CHAR = new PrimitiveType("char");
    public static final PrimitiveType BYTE = new PrimitiveType("byte");

    public PrimitiveType(String name) {
        super(name);
    }
}
