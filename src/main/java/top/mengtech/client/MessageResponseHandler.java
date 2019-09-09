package top.mengtech.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.packet.MessageResponsePacket;

import java.util.Date;

@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg){
        log.info(new Date() + "收到服务端消息：" + msg.getMessage());
    }
}
