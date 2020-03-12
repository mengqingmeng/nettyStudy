package top.mengtech.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.mengtech.packet.Packet;
import top.mengtech.packet.PacketCodeC;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out,msg);
    }
}
