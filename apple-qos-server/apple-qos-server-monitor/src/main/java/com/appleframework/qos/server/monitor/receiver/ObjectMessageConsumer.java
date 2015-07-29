package com.appleframework.qos.server.monitor.receiver;

import com.appleframework.qos.collector.core.URL;
import com.appleframework.qos.core.utils.ByteUtils;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.consumer.KafkaStream;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by haiyang on 15/7/29.
 */
public abstract class ObjectMessageConsumer {

    private ConsumerConfig consumerConfig;

    private String topic;

    private int partitionsNum;

    private ConsumerConnector consumer;

    private ExecutorService executorService;

    public void setConsumerConfig(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPartitionsNum(int partitionsNum) {
        this.partitionsNum = partitionsNum;
    }

    public void init() {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap;

        Map<String, Integer> topicCountFilter = new HashMap<String, Integer>();
        topicCountFilter.put(topic, new Integer(partitionsNum));

        consumerMap = consumer.createMessageStreams(topicCountFilter);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        executorService = Executors.newFixedThreadPool(partitionsNum);

        int threadNo = 0;
        for (final KafkaStream stream : streams) {
            executorService.submit(new ConsumerTask(stream, threadNo++));
        }

    }

    public void destroy() {
        if (consumer != null) {
            consumer.shutdown();
            consumer = null;
        }

        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
    }

    abstract void processMessage(Object msg);

    private class ConsumerTask implements Runnable {
        private KafkaStream stream;
        private int threadNo;

        public ConsumerTask(KafkaStream stream, int threadNo) {
            this.stream = stream;
            this.threadNo = threadNo;
        }

        public void run() {
            ConsumerIterator<byte[], byte[]> itr = stream.iterator();
            while (itr.hasNext()) {
                MessageAndMetadata<byte[], byte[]> messageAndMetadata = itr.next();

                final String topic = messageAndMetadata.topic();

                byte[] msg = messageAndMetadata.message();
                URL url = (URL)ByteUtils.toObject(msg);

                ObjectMessageConsumer.this.processMessage(url);
            }
        }
    }
}
