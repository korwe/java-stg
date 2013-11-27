package com.korwe.javastg.definition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Boxable extends ConcreteClass {
    private Primitive primitiveType;
    public static Boxable Boolean = new Boxable("java.lang","Boolean", Primitive.Boolean);
    public static Boxable Character = new Boxable("java.lang","Character", Primitive.Char);
    public static Boxable Byte = new Boxable("java.lang","Byte", Primitive.Byte);
    public static Boxable Short = new Boxable("java.lang","Short", Primitive.Short);
    public static Boxable Integer = new Boxable("java.lang","Integer", Primitive.Int);
    public static Boxable Long = new Boxable("java.lang","Long", Primitive.Long);
    public static Boxable Float = new Boxable("java.lang","Float", Primitive.Float);
    public static Boxable Double = new Boxable("java.lang","Double", Primitive.Double);
    public static Boxable String = new Boxable("java.lang","String", null);

    public Boxable(String packageName, String name, Primitive primitiveType) {
        super(packageName, name);
        if(primitiveType != null){
            primitiveType.setBoxableType(this);
        }
        this.primitiveType = primitiveType;
    }

    public Primitive getPrimitiveType() {
        return primitiveType;
    }

    public void setPrimitiveType(Primitive primitiveType) {
        this.primitiveType = primitiveType;
    }
}
