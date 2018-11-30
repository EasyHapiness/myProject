package com.ya.song.pictureserver.test.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKDBQueryProxy implements InvocationHandler {

    DBQuery dbQuery = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (dbQuery == null){
            dbQuery = new DBQuery();
        }
        return dbQuery.getString();
    }
}
