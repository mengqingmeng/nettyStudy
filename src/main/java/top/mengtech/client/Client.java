package top.mengtech.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.client.handler.FirstClientHandler;
import top.mengtech.packet.MessageRequestPacket;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Client  {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        connect(bootstrap,"localhost",8000,MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future->{
           if (future.isSuccess()){
               log.info("连接成功");
               Channel channel = ((ChannelFuture)future).channel();
//               startConsoleThread(channel);
           }else{
               log.info("连接失败，开始重连");
               int order = (MAX_RETRY - retry) + 1;
               int delay = 1 << order;
               log.info(new Date() + ": 连接失败，第" + order + "次重连……");
               bootstrap.config().group().schedule(()->connect(bootstrap, host, port,retry-1),delay, TimeUnit.SECONDS);

           }
        });
    }

    // 开启线程监听命令行输入
    private static void startConsoleThread(Channel channel){
        new Thread(()->{
            while (!Thread.interrupted()){
//                if(LoginUtil.hasLogin(channel)){
                    log.info("请输入消息：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket(line);
                    channel.writeAndFlush(packet);
//                }
            }
        }).start();
    }
}
