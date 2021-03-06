package top.mengtech.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                        nioSocketChannel.pipeline().addLast(new SocketHandler());
                    }
                });

        bind(serverBootstrap,PORT);
    }

    private static void bind(final ServerBootstrap serverBootStrap,final int port){
        serverBootStrap.bind(PORT).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    log.info("端口["+ port +"]绑定成功");
                }else{
                    log.error("端口["+ port +"]绑定失败");
                    bind(serverBootStrap,port + 1);
                }
            }
        });
    }
}
