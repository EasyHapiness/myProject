package com.ya.song.pictureserver.test.zhuangshiTest;

public abstract class PacketDecorator implements IPacketCreator{

    IPacketCreator component;

    public PacketDecorator(IPacketCreator info){
        component = info;
    }

}
