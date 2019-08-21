package top.mengtech.packet;

import lombok.Data;
import top.mengtech.command.Command;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String username;
    private String password;

    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
