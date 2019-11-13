package com.example.demo;

/**
 * @author GuoJingyuan
 * @date 2019/9/25
 */
public class Instance {
    private Instance(){

    }
    private volatile static Instance instance;

    public Instance getInstance(){
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
