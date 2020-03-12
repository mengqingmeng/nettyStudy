package top.mengtech;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.net.SocketServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BioDemo {

    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //socket server
        ServerSocket socketServer = new ServerSocket(7777);
        log.info("服务端启动了");
        // 监听客户端连接
        while (true){
            Socket socket = socketServer.accept(); //阻塞
            log.info("连接到客户端了");
            executorService.execute(()->{
                handler(socket);
            });
        }
        // 读取数据
    }

    private static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true){
                int lens = inputStream.read(bytes);
                if (lens != -1) {
                    log.info(new String(bytes,0,lens));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                log.info("关闭客户端连接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
