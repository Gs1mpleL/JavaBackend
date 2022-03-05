package JVM.直接内存;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存速度演示
 * @author wanfeng
 * @created 2022/3/4 17:45
 * @package JVM
 */
public class DirectBufferDome {
    static final String FROM ="E:\\DaBaiCai\\DaBaiCai.exe";
    static final String TO = "E:\\test.exe";
    static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) {
        io();   // io用时 -> 11.150684931506849
        directBuffer();   // DirectBuffer用时 -> 5.187481251874813
    }
    private static void directBuffer(){
        long start =  System.nanoTime();
        try (FileChannel fileInputStream = new FileInputStream(FROM).getChannel(); FileChannel to = new FileOutputStream(TO).getChannel()   ){
            ByteBuffer buff = ByteBuffer.allocateDirect(_1Mb);
            while (true){
                int len = fileInputStream.read(buff);
                if(len == -1){
                    break;
                }
                to.write(buff);
                buff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("DirectBuffer用时 -> " + (end-start)/1000_100.0);

    }
    private static void io(){
        long start =  System.nanoTime();
        try (FileInputStream fileInputStream = new FileInputStream(FROM);FileOutputStream to = new FileOutputStream(TO)){
            byte[] buff= new byte[_1Mb];
            while (true){
                int len = fileInputStream.read(buff);
                if(len == -1){
                    break;
                }
                to.write(buff,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("io用时 -> " + (end-start)/1000_100.0);
    }
}
