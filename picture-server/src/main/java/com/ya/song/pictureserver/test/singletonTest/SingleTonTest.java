package com.ya.song.pictureserver.test.singletonTest;

public class SingleTonTest {

    private SingleTonTest(){
        System.out.println("Singleton is creating");
    }

    private static class  SingleTonHoleder{

        private static SingleTonTest instance = new SingleTonTest();
    }

    private static SingleTonTest getInstance(){

        return SingleTonHoleder.instance;
    }
}
