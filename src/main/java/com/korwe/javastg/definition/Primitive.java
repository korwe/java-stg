package com.korwe.javastg.definition;

import com.korwe.javastg.type.Type;
import com.korwe.javastg.type.TypeDefinition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Primitive extends TypeDefinition {
    private Boxable boxableType;
    public static final Primitive Boolean = new Primitive("boolean", Boxable.Boolean);
    public static final Primitive Short = new Primitive("short", Boxable.Short);
    public static final Primitive Int = new Primitive("int", Boxable.Integer);
    public static final Primitive Long = new Primitive("long", Boxable.Long);
    public static final Primitive Float = new Primitive("float", Boxable.Float);
    public static final Primitive Double = new Primitive("double", Boxable.Double);
    public static final Primitive Char = new Primitive("char", Boxable.Character);
    public static final Primitive Byte = new Primitive("byte", Boxable.Byte);

    public Primitive(String name, Boxable boxableType) {
        super(name);
        if(boxableType != null){
            boxableType.setPrimitiveType(this);
        }
        this.boxableType = boxableType;
    }

    public Boxable getBoxableType() {
        return boxableType;
    }

    public void setBoxableType(Boxable boxableType) {
        this.boxableType = boxableType;
    }

    @Override
    public boolean isCompatibleWith(Type type){
        if(type == null) return false;

        if(Primitive.class.isAssignableFrom(type.getClass())){
            //Check if the types are the same
            if(type.equals(this)){
                return true;
            }
            else{
                //Check boxable type is the same
                return this.getBoxableType() != null && this.getBoxableType().equals(((Primitive)type).getBoxableType());
            }
        }
        else{
            return type.equals(this.getBoxableType());
        }
    }
}
