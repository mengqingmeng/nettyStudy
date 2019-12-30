package top.mengtech;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9,100);

        print("allocate ByteBuf(9,100)",buffer);

        buffer.writeBytes(new byte[]{1,2,3,4});
        print("write bytes(1,2,3,4)",buffer);

        buffer.writeInt(12);
        print("writeInt(12)", buffer);

        buffer.writeBytes(new byte[]{5});
        print("writeBytes(5)", buffer);

        buffer.writeBytes(new byte[]{6});
        print("writeBytes(6)", buffer);

        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getShort(3) return: " + buffer.getShort(3));
        System.out.println("getInt(3) return: " + buffer.getInt(3));
        print("getByte()", buffer);
    }

    private static void print(String action, ByteBuf buffer) {
        System.out.println("after ===========" + action + "============");
        System.out.println("容量，capacity(): " + buffer.capacity());
        System.out.println("最大容量，maxCapacity(): " + buffer.maxCapacity());
        System.out.println("读指针，readerIndex(): " + buffer.readerIndex());
        System.out.println("可读字节数，readableBytes(): " + buffer.readableBytes());
        System.out.println("可读，isReadable(): " + buffer.isReadable());
        System.out.println("写指针，writerIndex(): " + buffer.writerIndex());
        System.out.println("可写字节数，writableBytes(): " + buffer.writableBytes());
        System.out.println("可写，isWritable(): " + buffer.isWritable());
        System.out.println("最大可写，maxWritableBytes(): " + buffer.maxWritableBytes());
        System.out.println();
    }
}
