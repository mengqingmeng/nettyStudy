package top.mengtech.packet;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.mengtech.command.Command;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String message;
    public MessageRequestPacket(String message) {
        this.message = message;
    }
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
