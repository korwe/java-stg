package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class BoxableType extends ConcreteClass{
    private PrimitiveType primitiveType;
    public static BoxableType Boolean = new BoxableType("java.lang","Boolean", PrimitiveType.Boolean);
    public static BoxableType Character = new BoxableType("java.lang","Character", PrimitiveType.Char);
    public static BoxableType Byte = new BoxableType("java.lang","Byte", PrimitiveType.Byte);
    public static BoxableType Short = new BoxableType("java.lang","Short", PrimitiveType.Short);
    public static BoxableType Integer = new BoxableType("java.lang","Integer", PrimitiveType.Int);
    public static BoxableType Long = new BoxableType("java.lang","Long", PrimitiveType.Long);
    public static BoxableType Float = new BoxableType("java.lang","Float", PrimitiveType.Float);
    public static BoxableType Double = new BoxableType("java.lang","Double", PrimitiveType.Double);
    public static BoxableType String = new BoxableType("java.lang","String", null);

    public BoxableType(String packageName, String name, PrimitiveType primitiveType) {
        super(packageName, name);
        this.primitiveType = primitiveType;
    }

    public PrimitiveType getPrimitiveType() {
        return primitiveType;
    }

    public void setPrimitiveType(PrimitiveType primitiveType) {
        this.primitiveType = primitiveType;
    }
}
