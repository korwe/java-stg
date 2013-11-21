package com.korwe.javastg.util;

import com.korwe.javastg.type.AccessModifier;
import com.korwe.javastg.type.Attribute;
import com.korwe.javastg.type.ConcreteMethod;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class MethodUtil {
    public static ConcreteMethod getterMethod(Attribute attribute){
        String name = "get"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        method.setReturnType(attribute.getType());
        method.setReturnValue("this."+attribute.getName());
        return method;
    }

    public static ConcreteMethod setterMethod(Attribute attribute){
        String name = "set"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        Attribute parameter = new Attribute();
        parameter.setName(attribute.getName());
        parameter.setType(attribute.getType());
        method.addParamater(parameter);

        method.setBody("this." + attribute.getName() + " = " + attribute.getName());

        return method;

    }
}
