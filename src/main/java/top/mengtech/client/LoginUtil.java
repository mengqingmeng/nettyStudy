package top.mengtech.client;

import io.netty.channel.Channel;
import top.mengtech.attributes.Attributes;

public class LoginUtil {

    public static void martAsLogin(Channel chanel){
        chanel.attr(Attributes.LOGIN).set(true);
    }
}
