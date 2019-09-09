package top.mengtech.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import top.mengtech.serializer.Serializer;
import top.mengtech.serializer.impl.JSONSerializer;

import java.util.HashMap;
import java.util.Map;

import static top.mengtech.command.Command.*;

@Slf4j
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte,Class<? extends Packet>> packetTypeMap; // 数据包类型map
    private final Map<Byte,Serializer> serializerMap; // 序列化map
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC() {
        // 数据包类型Map
        packetTypeMap = new HashMap<Byte, Class<? extends Packet>>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        // 序列化方法Map
        serializerMap = new HashMap<Byte, Serializer>();
        Serializer jsonSerializer = new JSONSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(),jsonSerializer);
    }

    public ByteBuf encode(Packet packet){
        // 1 创建ByteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        buffer.writeInt(MAGIC_NUMBER); // 魔数
        buffer.writeByte(packet.getVersion()); // 版本
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm()); // 序列化算法
        buffer.writeByte(packet.getCommand()); // 指令
        buffer.writeInt(bytes.length); // 长度
        buffer.writeBytes(bytes); // 数据
        return buffer;
    }

    public void encode(ByteBuf out,Packet packet){
        out = encode(packet);
    }

    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4); // 跳过魔数
        byteBuf.skipBytes(1); //跳过版本
        byte serializerAlgorithm = byteBuf.readByte(); // 序列化算法类型
        byte command = byteBuf.readByte(); // 指令
        int length = byteBuf.readInt(); // 长度
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(requestType!=null && serializer !=null){
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command){
        Class packetType = packetTypeMap.get(command);
        if( packetType == null){
            log.error("未找到Packet定义");
            return null;
        }
        return packetTypeMap.get(command);
    }

    private Serializer getSerializer(byte serializerAlgorithm){
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        if(serializer == null){
            log.error("为找到序列化方法");
            return null;
        }
        return serializerMap.get(serializerAlgorithm);
    }
}
