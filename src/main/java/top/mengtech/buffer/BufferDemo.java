package top.mengtech.buffer;

import java.nio.IntBuffer;

public class BufferDemo {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 写
        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i * 2);
        }

        // 转换模式
        intBuffer.flip();

        // 读
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
