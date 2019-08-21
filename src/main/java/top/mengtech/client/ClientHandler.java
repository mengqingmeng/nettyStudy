package top.mengtech.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客户端写出数据");
        ByteBuf buffer = getBytes(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ":客户端读到数据->" + byteBuf.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getBytes(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        byte[] bytes = "你好".getBytes("utf-8");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
