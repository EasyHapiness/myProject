package com.ya.song.pictureserver.test.NIOTest;

import com.ya.song.pictureserver.entity.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

    public static void getInfo() throws IOException {

        String resouce = "G:\\NIOReadTest.txt";
        String destation = "G:\\NIOWriteTest.txt";
        FileInputStream fileInputStream = new FileInputStream(new File(resouce));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(destation));

        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writeChannl = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){
            byteBuffer.clear();
            int flag = readChannel.read(byteBuffer);
            if (flag == -1){
              break;
            }
            byteBuffer.flip();
            writeChannl.write(byteBuffer);
        }
        readChannel.close();
        writeChannl.close();
    }

    public static void main(String[] args) {

        try {
            getInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("NIOTest is over!");


        //创建buffer有两种方式
        //第一种
//        byte[] bytes = new byte[1024];
//        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        //第二种
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        for (int i= 0;i<10;i++){
            byteBuffer1.put((byte)i);
        }

        byteBuffer1.flip();
        for (int i= 0;i<byteBuffer1.limit();i++){
            System.out.println(byteBuffer1.get());
            if (i == 4){
                byteBuffer1.mark();
                System.out.println("mark at " + i);
            }
        }

        byteBuffer1.reset();
        System.out.println("===========000000000000 ");

        while (byteBuffer1.hasRemaining()){
            System.out.println("byteBuffer1 "+ byteBuffer1.get());
        }

        System.out.println("===================================== ");

        byteBuffer1.position(3);
        byteBuffer1.limit(10);

        ByteBuffer byteBuffer = byteBuffer1.slice();

        while (byteBuffer.hasRemaining()){
            System.out.println("byteBuffer "+byteBuffer.get());
        }

        System.out.println("++++++++++++++++++++++++");
        while (byteBuffer.hasRemaining()){
            if (byteBuffer.get() == 5){
                byteBuffer.put(byteBuffer.position(),(byte) 4);
            }
            System.out.println("byteBuffer1 + byteBuffer " +byteBuffer1.get());
        }
    }
}
