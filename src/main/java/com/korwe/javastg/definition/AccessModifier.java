package com.korwe.javastg.definition;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public enum AccessModifier {
    Public,
    Private,
    Protected;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
