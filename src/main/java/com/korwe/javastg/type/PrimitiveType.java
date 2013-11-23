package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class PrimitiveType extends TypeDefinition{
    private BoxableType boxableType;
    public static final PrimitiveType Boolean = new PrimitiveType("boolean", BoxableType.Boolean);
    public static final PrimitiveType Short = new PrimitiveType("short", BoxableType.Short);
    public static final PrimitiveType Int = new PrimitiveType("int", BoxableType.Integer);
    public static final PrimitiveType Long = new PrimitiveType("long", BoxableType.Long);
    public static final PrimitiveType Float = new PrimitiveType("float", BoxableType.Float);
    public static final PrimitiveType Double = new PrimitiveType("double", BoxableType.Double);
    public static final PrimitiveType Char = new PrimitiveType("char", BoxableType.Character);
    public static final PrimitiveType Byte = new PrimitiveType("byte", BoxableType.Byte);

    public PrimitiveType(String name, BoxableType boxableType) {
        super(name);
        if(boxableType != null){
            boxableType.setPrimitiveType(this);
        }
        this.boxableType = boxableType;
    }

    public BoxableType getBoxableType() {
        return boxableType;
    }

    public void setBoxableType(BoxableType boxableType) {
        this.boxableType = boxableType;
    }
}
