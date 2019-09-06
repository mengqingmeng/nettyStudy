package top.mengtech.packet;

import lombok.Data;
import top.mengtech.command.Command;

@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
