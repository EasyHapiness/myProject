package com.ya.song.pictureserver.test.zhuangshiTest;

public class TestMain {

    public static void main(String[] args) {
        IPacketCreator iPacketCreator = new PacketHTMLCreator(new PacketBodyCreator());

        System.out.println(iPacketCreator.handleContent());
    }
}
