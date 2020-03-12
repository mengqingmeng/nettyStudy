package top.mengtech.client;

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
import java.util.Date;
import java.util.UUID;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    // 客户端连接服务端成功后 执行的函数
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
<<<<<<< HEAD
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("testUserName");
        packet.setPassword("testPassword");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),packet);
        ctx.channel().writeAndFlush(buffer);
=======
        log.info("客户端开始登录");
        // 登陆数据对象
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        requestPacket.setUsername("测试登陆");
        requestPacket.setPassword("abc");
        requestPacket.setUserId(UUID.randomUUID().toString());

        // 编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(requestPacket);

        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
<<<<<<< HEAD

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            System.out.println("登陆状态：" + loginResponsePacket.isSuccess());
=======
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                log.info(new Date() + ": 客户端登录成功");
            } else {
                log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf
        }
    }
}
