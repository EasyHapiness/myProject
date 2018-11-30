package com.ya.song.pictureserver.entity;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.collect.Sets.union;

public class TestMain {
    public static void main(String[] args) {

        User user = new User.Builder(1,1).mobile(1830070).sex(1).build();
        System.out.println(user.toString());
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i =0;i<10;i ++){
            System.out.println(atomicInteger.get());
            System.out.println("============ "+atomicInteger.addAndGet(1));
        }

        Set<String> set = new TreeSet<>();
        Collections.addAll(set,args);
        System.out.println(set);


        Set<String> setList = new HashSet<>();

        Arrays.asList("tom","jack","timy");

        Set<String> setLists = new HashSet<>();

        Arrays.asList("tom1","jack1","timy1");

        Set<String> test = union(setList,setLists);

        System.out.println("0000000000000 "+test);

        System.out.println(getString("yinyasong"));

        String string =null;
        long start = System.currentTimeMillis();
        for (int i =0;i <10000;i++){
            string = string + i+"";

        }
        System.out.println(System.currentTimeMillis() - start);


        StringBuffer strings = new StringBuffer();
        long start1 = System.currentTimeMillis();
        long begin = System.nanoTime();
        for (int i =0;i <10000;i++){
            strings = strings.append(i);

        }
        System.out.println(System.currentTimeMillis() - start1);

        System.out.println(System.nanoTime() - begin);
    }

    public static  <T> T getString(T t){
        T t1 = null;
        if (t instanceof  String){
            t1 = t;
        }
        return  t1;
    }
}
