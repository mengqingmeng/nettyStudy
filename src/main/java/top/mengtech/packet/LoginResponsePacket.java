package top.mengtech.packet;

import lombok.Data;
import top.mengtech.command.Command;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
