package top.mengtech.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.mengtech.packet.LoginRequestPacket;
import top.mengtech.packet.LoginResponsePacket;
import top.mengtech.packet.Packet;
import top.mengtech.packet.PacketCodeC;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    // 客户端连接服务端成功后 执行的函数
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("testUserName");
        packet.setPassword("testPassword");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),packet);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            System.out.println("登陆状态：" + loginResponsePacket.isSuccess());
        }
    }
}
