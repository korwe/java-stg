package com.korwe.javastg.definition;

import com.korwe.javastg.type.*;
import com.korwe.javastg.type.Class;
import com.korwe.javastg.value.TypeValue;

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
    public boolean extendsFrom(ClassType classType){
        if(this.equals(classType)){
            List<TypeParameter> typeParameters = getGenerifiable().getTypeParameters();
            for (int i = 0; i < typeParameters.size(); i++) {
                TypeParameter typeParameter = typeParameters.get(i);
                for(Reference param : getParameterTypes()){
                    for (Reference reference : typeParameter.getParentTypes()) {
                        if(!reference.isCompatibleWith(new TypeValue(param) {
                            @Override
                            public String getCodeString() {
                                return null;
                            }
                        })) return false;
                    }

                }
            }

            return true;
        }

        if(classType.getSuperClass() != null){
            return extendsFrom(classType.getSuperClass());
        }

        return false;
    }
}
