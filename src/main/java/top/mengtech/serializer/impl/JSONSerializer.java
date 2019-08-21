package top.mengtech.serializer.impl;

import com.alibaba.fastjson.JSON;
import top.mengtech.serializer.Serializer;
import top.mengtech.serializer.SerializerAlgorithm;

/**
 * json 序列化和反序列化工具
 */
public class JSONSerializer implements Serializer {
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
