package top.mengtech.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.packet.MessageRequestPacket;
import top.mengtech.packet.MessageResponsePacket;
import top.mengtech.packet.PacketCodeC;

import java.util.Date;

@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        log.info("服务端channelId:" + ctx.channel().id().toString());
        log.info(new Date() + "收到客户端消息：" + msg.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("收到你的消息啦");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
