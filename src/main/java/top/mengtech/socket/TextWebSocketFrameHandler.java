package top.mengtech.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

//        @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception { // 处理自定义事件
//        if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){ // 握手完成
//            // 如果握手成功，则从该Channelpipeline 中移除Http RequestHandler,因为不需要接收Http消息了
////            ctx.pipeline().remove(HttpRequestHandler.class);
//
//            group.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined"));
//            group.add(ctx.channel());
//        }else{
//            super.userEventTriggered(ctx, evt);
//        }
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("msg:" + msg.text());
    }
}
