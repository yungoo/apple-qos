package com.appleframework.qos.server.agent;

import com.appleframework.qos.collector.core.URL;

import com.appleframework.qos.core.utils.ByteUtils;
import com.appleframework.qos.core.utils.KryoSerializer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

public class MonitorSendService {
	
	private Producer<String, byte[]> producer;

	private String topic;

	private KryoSerializer kryoSerializer;

	public void setProducer(Producer<String, byte[]> producer) {
		this.producer = producer;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void init() {
		kryoSerializer = new KryoSerializer();
		try {
			kryoSerializer.init();
			kryoSerializer.register(URL.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(URL url) {
//		byte[] dataBytes = ByteUtils.toBytes(url);
		byte[] dataBytes = kryoSerializer.serialize(url);
		KeyedMessage<String, byte[]> producerData = new KeyedMessage<String, byte[]>(
				topic, dataBytes);
		producer.send(producerData);
	}

	public void destroy() {
		if (null != producer)
			producer.close();
	}
}
