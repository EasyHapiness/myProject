package com.ya.song.pictureserver.entity;

public class User {

    private final int name;

    private final int age;

    private final int mobile;

    private final int sex;

    public static class Builder{

        private int name;

        private int age;

        private int mobile;

        private int sex;

        public Builder (int age,int name){
            this.age = age;
            this.name = name;
        }

        public Builder mobile(int value){
            this.mobile = value;
            return this;
        }

        public Builder sex(int value){
            this.sex = value;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public User(Builder builder){
        this.age =builder.age;
        this.name = builder.name;
        this.mobile = builder.mobile;
        this.sex = builder.sex;
    }
}
