package com.appleframework.qos.core.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.*;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.MapSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: KryoSerializer
 * @Description: Kryo的序列化和反序列化实现类
 * @author 简道
 * @date 2011-9-16 下午12:06:15
 * 
 */
public class KryoSerializer {

	private static final String OBJECT_BUFFER = "ObjectBuffer";

	private Kryo kryo;
	/**
	 * @Fields initialCapacity : 初始容量
	 */
	private int initialCapacity = 512;
	/**
	 * @Fields maxCapacity : 最大容量
	 */
	private int maxCapacity = 5 * 1024 * 1024;

	private List<Class<?>> registeredClass;

	public KryoSerializer() {
		this.kryo = new Kryo();
//		kryo.register(BigInteger.class, new DefaultSerializers.BigIntegerSerializer());
//		kryo.register(BigDecimal.class, new DefaultSerializers.BigDecimalSerializer());
//		kryo.register(ConcurrentHashMap.class, new MapSerializer());
//		kryo.register(Date.class, new DefaultSerializers.DateSerializer());
	}

	public void init() throws ClassNotFoundException {
		if (registeredClass != null) {
			for (Class<?> clazz : registeredClass) {
				register(clazz);
			}
		}
	}

	public void register(Class<?> clazz) {
		kryo.register(clazz);
	}

	public byte[] serialize(Object object) {
		ByteBufferOutput output = new ByteBufferOutput(initialCapacity, maxCapacity);
		kryo.writeClassAndObject(output, object);

		return output.toBytes();
	}

	public Object deserialize(byte[] bytes) throws Exception {
		Input input = new Input(bytes);
		return kryo.readClassAndObject(input);
	}

	public void setRegisteredClass(List<Class<?>> registeredClass) {
		this.registeredClass = registeredClass;
	}

	public void setRegisteredClass(Class<?>... registeredClass) {
		if (registeredClass != null) {
			this.registeredClass = Arrays.asList(registeredClass);
		}
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

}
