package com.korwe.javastg.util;

import com.korwe.javastg.type.AccessModifier;
import com.korwe.javastg.type.Attribute;
import com.korwe.javastg.type.Method;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class MethodUtil {
    public static Method getterMethod(Attribute attribute){
        String name = "get"+attribute.getCapitalizedName();
        Method method = new Method(AccessModifier.Public, name);

        method.setReturnType(attribute.getType());
        method.setReturnValue("this."+attribute.getName());
        return method;
    }

    public static Method setterMethod(Attribute attribute){
        String name = "set"+attribute.getCapitalizedName();
        Method method = new Method(AccessModifier.Public, name);

        Attribute parameter = new Attribute();
        parameter.setName(attribute.getName());
        parameter.setType(attribute.getType());
        method.addParamater(parameter);

        method.setBody("this." + attribute.getName() + " = " + attribute.getName());

        return method;

    }
}
