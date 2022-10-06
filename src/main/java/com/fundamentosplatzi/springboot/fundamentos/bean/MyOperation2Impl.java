package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyOperation2Impl extends MyOperationImpl {

    public int sum(int number){

        return super.sum(number)+1;
    }
}
