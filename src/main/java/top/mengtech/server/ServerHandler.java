package top.mengtech.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf
import top.mengtech.packet.LoginRequestPacket;
import top.mengtech.packet.LoginResponsePacket;
import top.mengtech.packet.Packet;
import top.mengtech.packet.PacketCodeC;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
<<<<<<< HEAD
        ByteBuf buffer = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buffer);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            System.out.println("登陆成功："+ loginRequestPacket.getUsername());

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setSuccess(true);
            responsePacket.setReason("登陆成功");
=======
        // 接收数据
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            log.info(new Date()+ "登陆成功：" + loginRequestPacket.getUsername());

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setReason("登陆成功");
            responsePacket.setSuccess(true);
            ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(responsePacket));
        }
    }
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf

            ByteBuf response = PacketCodeC.INSTANCE.encode(ctx.alloc(),responsePacket);
            ctx.channel().writeAndFlush(response);
        }
    }

    public void responseLogin(ChannelHandlerContext ctx){

    }
}
