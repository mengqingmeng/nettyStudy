package top.mengtech.packet;

import lombok.Data;
<<<<<<< HEAD
import top.mengtech.command.Command;

@Data
public class LoginResponsePacket extends Packet {

=======
import lombok.Getter;
import lombok.Setter;
import top.mengtech.command.Command;

public class LoginResponsePacket extends Packet {
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
<<<<<<< HEAD
=======

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
>>>>>>> 632911c6b7d9cef0788cf8a20596bf95c9474cdf
}
