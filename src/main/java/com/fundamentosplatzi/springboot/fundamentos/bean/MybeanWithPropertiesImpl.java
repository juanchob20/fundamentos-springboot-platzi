package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MybeanWithPropertiesImpl implements MybeanWithProperties{

    private String nombre;
    private String apellido;

    public MybeanWithPropertiesImpl(String nombre,String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }


    @Override
    public String function() {
        return nombre+" "+apellido;
    }
}
