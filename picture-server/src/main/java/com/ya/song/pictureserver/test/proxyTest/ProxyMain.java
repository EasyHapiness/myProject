package com.ya.song.pictureserver.test.proxyTest;

import java.lang.reflect.Proxy;

public class ProxyMain {

    public static void main(String[] args) {
        IDBQuery dbQuery = createIDBQuery();
        System.out.println(dbQuery.getString());

        System.out.println("====== " + dbQuery.getName("yinyasong"));
    }

    public static IDBQuery createIDBQuery(){

        IDBQuery idbQuery = (IDBQuery)Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),new Class[]{IDBQuery.class},new JDKDBQueryProxy());

        return idbQuery;
    }
}
