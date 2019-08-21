package top.mengtech.serializer;

import top.mengtech.serializer.impl.JSONSerializer;

/**
 * 序列化接口
 */
public interface Serializer {
    /**
     * 序列化算法
     * @return 算法代表的byte值
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     * @param object 被序列化的对象
     * @return 序列化后的二进制
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     * @param clazz 反序列化后的对象
     * @param bytes 序列化后的二进制
     * @param <T>   反序列化后的类型
     * @return 反序列化后的对象
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);

    byte JSON_SERIALIZER = SerializerAlgorithm.JSON;
    Serializer DEFAULT = new JSONSerializer();
}
