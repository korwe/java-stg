package com.korwe.javastg.definition;

import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;

import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class ParameterizedClass extends ParameterizedType implements ClassType {

    protected ParameterizedClass(Class generifiableType) {
        super(generifiableType);
    }

    @Override
    public ClassType getSuperClass() {
        return ((Class)getGenerifiable()).getSuperClass();
    }

    @Override
    public void setSuperClass(ClassType superClass) {
        ((Class)getGenerifiable()).setSuperClass(superClass);
    }


    @Override
    public boolean isCompatibleWith(Type type){
        return super.isCompatibleWith(type) || extendsFrom((ClassType)type);
    }

    @Override
    public boolean extendsFrom(ClassType classType){
        if(classType == null) return false;

        if(this.equals(classType)){
            List<TypeParameter> typeParameters = getGenerifiable().getTypeParameters();
            for (int i = 0; i < typeParameters.size(); i++) {
                if(!typeParameters.get(i).isCompatibleWith(getParameterTypes().get(i))) return false;
            }
            return true;
        }

        if(classType.getSuperClass() != null){
            return extendsFrom(classType.getSuperClass());
        }
        return false;
    }
}
