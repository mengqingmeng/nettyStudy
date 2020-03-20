package top.mengtech.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannel01 {
    public static void main(String[] args) throws IOException {
        String text = "hello,中国人";
        FileOutputStream fileOutputStream = new FileOutputStream("./test.txt");

        FileChannel channel = fileOutputStream.getChannel();

        CharBuffer charBuffer = CharBuffer.wrap(text);

        ByteBuffer byteBuffer = Charset.forName("utf-8").encode(charBuffer);

        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
