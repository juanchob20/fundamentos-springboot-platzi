package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyOtherBeanImpl implements MyOtherBean{
    @Override
    public void printNumbers() {
        System.out.println("Mis numeros");
        for (int i = 0; i <5 ; i++) {
            System.out.println(i);
        }
    }
}
