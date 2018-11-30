package com.ya.song.pictureserver.test;

import java.util.*;

public class PoolObject {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a " + a + "," + "b" + b);


        String s = "test,info";
        char[] chars = s.toCharArray();

        chars[0] = +32;

        System.out.println(new String(chars));

        s.substring(0, 4);
        s.split("");

        List<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(s, ",");

        while (stringTokenizer.hasMoreTokens()) {

            list.add(stringTokenizer.nextToken());
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.size());

        Map<String,String> map = new HashMap<>();


        char d = 'a';
        char e = '0';
        char f = 1110;

        int g = '国' + '国'+ '国';
        System.out.println("d " + d + " e " + e);

        System.out.println( " f " + g);

        for (int i =0;i <chars.length;i++){
            //System.out.println(chars[i]);
        }

}
}
