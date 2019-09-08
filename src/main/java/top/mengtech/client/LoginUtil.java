package top.mengtech.client;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import top.mengtech.attributes.Attributes;

public class LoginUtil {

    public static void martAsLogin(Channel chanel){
        chanel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr != null;
    }
}
