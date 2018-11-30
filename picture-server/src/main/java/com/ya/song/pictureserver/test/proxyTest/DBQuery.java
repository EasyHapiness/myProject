package com.ya.song.pictureserver.test.proxyTest;

public class DBQuery implements IDBQuery {

    public DBQuery(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getString() {
        return "This is proxy Test";
    }

    @Override
    public String getName(String name) {
        return name;
    }
}
