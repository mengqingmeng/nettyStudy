package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        ServerSocket server= new ServerSocket(port);
        System.out.println("时间服务启动，端口："+ port);;

        try {
            while(true){
                Socket client = server.accept();
                System.out.println("接收新连接：" + client.toString());
                new Thread(new TimeServerHandler(client)).start();
            }

        } catch (IOException e){
            System.out.println("服务启动失败");
        }
    }
}
