package com.korwe.javastg.type;

import com.korwe.javastg.definition.Reference;
import com.korwe.javastg.definition.TypeParameter;
import com.korwe.javastg.value.TypeValue;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public abstract class Class extends MemberContainer implements ClassType{
    private ClassType superClass;

    public Class(String name) {
        super(name);
    }

    public Class(String packageName, String name) {
        super(packageName, name);
    }

    public Class(String packageName, String name, ClassType superClass) {
        super(packageName, name);
        this.superClass = superClass;
    }

    @Override
    public ClassType getSuperClass() {
        return superClass;
    }

    @Override
    public void setSuperClass(ClassType superClass) {
        this.superClass = superClass;
    }

    @Override
    public boolean isCompatibleWith(TypeValue value){
        if(super.isCompatibleWith(value))return true;
        if(ClassType.class.isAssignableFrom(value.getType().getClass())){
            if(extendsFrom((ClassType)value.getType())){
                if(ParameterizedType.class.isAssignableFrom(value.getType().getClass())){
                    return validParameterTypes((ParameterizedType)value.getType());
                }
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean extendsFrom(ClassType classType){
        if(this.equals(classType))return true;

        if(classType.getSuperClass() != null){
            return extendsFrom(classType.getSuperClass());
        }

        return false;
    }

    private boolean validParameterTypes(ParameterizedType parameterizedType){
        for (int i = 0; i < getTypeParameters().size(); i++) {
            TypeParameter typeParameter = getTypeParameters().get(i);
            for(Reference param : parameterizedType.getParameterTypes()){
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
}
