package bio;

import java.io.*;
import java.net.Socket;
import java.time.Instant;

public class TimeServerHandler implements Runnable{
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

            try {
                InputStream inputStream = socket.getInputStream();
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // 读取消息
                while(true){
                    byte[] data = new byte[1024];
                    int len;
                    while ((len = inputStream.read(data)) !=-1){
                        String message = new String(data,0,len);
                        String currentTime = "QUERY".equalsIgnoreCase(message) ? Instant.now().toString() : "BAD QUERY";
                        System.out.println("客户端传来消息：" + message);
                        out.println(currentTime);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
