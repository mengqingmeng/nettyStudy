package top.mengtech.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.packet.LoginRequestPacket;
import top.mengtech.packet.LoginResponsePacket;
import top.mengtech.packet.Packet;
import top.mengtech.packet.PacketCodeC;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收数据
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            System.out.println(new Date()+ "登陆成功：" + loginRequestPacket.getUsername());

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setReason("登陆成功");
            responsePacket.setSuccess(true);
            ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(responsePacket));
        }
        ctx.channel().writeAndFlush(PacketCodeC.INSTANCE.encode(null));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        byte[] bytes = "欢迎".getBytes("utf-8");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public void responseLogin(ChannelHandlerContext ctx){

    }
}
