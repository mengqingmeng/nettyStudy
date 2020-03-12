package top.mengtech.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

public class ChatServer {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private final EventLoopGroup loopGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(InetSocketAddress address){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(loopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatServerInitializer());

        ChannelFuture future = bootstrap.bind(address);
        future.syncUninterruptibly();
        channel = future.channel();
        return future;
    }

    public void destroy(){
        if(channel !=null){
            channel.close();
        }
//        channelGroup.close();
        loopGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
//        if(args.length !=1){
//            System.err.println("Please give port as argument");
//            System.exit(1);
//        }

        int port = 8888;
        final ChatServer chatServer = new ChatServer();
        ChannelFuture future = chatServer.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread(()->{chatServer.destroy();}));
        future.channel().closeFuture().syncUninterruptibly();
    }
}
