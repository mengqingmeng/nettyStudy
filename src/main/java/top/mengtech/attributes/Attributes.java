package top.mengtech.attributes;

import io.netty.util.AttributeKey;

public interface Attributes {
    // 登陆成功标志位
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
