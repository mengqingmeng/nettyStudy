package top.mengtech.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Client  {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap,"localhost",8000,MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future->{
           if (future.isSuccess()){
               System.out.println("连接成功");
           }else{
               System.err.println("连接失败，开始重连");
               int order = (MAX_RETRY - retry) + 1;
               int delay = 1 << order;
               System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
               bootstrap.config().group().schedule(()->connect(bootstrap, host, port,retry-1),delay, TimeUnit.SECONDS);

           }
        });
    }
}
