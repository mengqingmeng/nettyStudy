package top.mengtech.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "：服务端接收到数据->" + byteBuf.toString(Charset.forName("utf-8")));

        // 发送数据
        System.out.println(new Date() + ": 服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        byte[] bytes = "欢迎".getBytes("utf-8");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
