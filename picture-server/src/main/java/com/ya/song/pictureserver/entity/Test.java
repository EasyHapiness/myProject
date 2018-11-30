package com.ya.song.pictureserver.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Test extends TestAbsc {

    @Override
    public String getSex(String sex) {
        return sex;
    }

    public  String getInfo(){
        return getSex("nan");
    }
    public static void main(String[] args) {

        System.out.println(new Test().getSex("女"));

//        Map<String,Object> map = new HashMap<>();
//        for (Map.Entry info : map.entrySet()){
//
//        }

        String [] arr = {"2018-11-16 16:16","2018-11-16 16:15","2018-11-16 16:19","2018-11-16 16:10"};

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
