package top.mengtech.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.packet.LoginRequestPacket;
import top.mengtech.packet.LoginResponsePacket;
import top.mengtech.packet.PacketCodeC;

import java.util.Date;

// 处理登录请求
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        log.info(new Date()+ "登陆成功：" + msg.getUsername());

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setReason("登陆成功");
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}