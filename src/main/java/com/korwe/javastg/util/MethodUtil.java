package com.korwe.javastg.util;

import com.korwe.javastg.type.AccessModifier;
import com.korwe.javastg.type.ClassAttribute;
import com.korwe.javastg.type.ConcreteMethod;
import com.korwe.javastg.type.Parameter;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class MethodUtil {
    public static ConcreteMethod getterMethod(ClassAttribute attribute){
        String name = "get"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        method.setReturnType(attribute.getType());
        method.setReturnValue("this."+attribute.getName());
        return method;
    }

    public static ConcreteMethod setterMethod(ClassAttribute attribute){
        String name = "set"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        Parameter parameter = new Parameter(attribute.getType(), attribute.getName());
        method.addParamater(parameter);

        method.setBody("this." + attribute.getName() + " = " + attribute.getName());

        return method;

    }
}
