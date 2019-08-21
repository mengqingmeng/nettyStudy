package top.mengtech.server;

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

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buffer);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            System.out.println("登陆成功："+ loginRequestPacket.getUsername());

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setSuccess(true);
            responsePacket.setReason("登陆成功");

            ByteBuf response = PacketCodeC.INSTANCE.encode(ctx.alloc(),responsePacket);
            ctx.channel().writeAndFlush(response);
        }
    }
}
