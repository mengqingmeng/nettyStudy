package top.mengtech.packet;

import top.mengtech.command.Command;

public class MessageResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
