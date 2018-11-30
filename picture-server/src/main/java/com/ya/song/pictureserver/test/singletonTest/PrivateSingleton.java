package com.ya.song.pictureserver.test.singletonTest;

public class PrivateSingleton {

    private PrivateSingleton(){
        System.out.println("privateSingleton is creating");
    }

    private static class SingletonHoler{
        private static PrivateSingleton instance = new PrivateSingleton();
    }

    private static PrivateSingleton getInstance(){
        return SingletonHoler.instance;
    }
}
