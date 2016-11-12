package org.acme.flink.kafka.util;

import java.io.IOException;
import java.util.Properties;

import org.acme.flink.kafka.StreamingKafkaConsumerJob;

public class PropertiesUtil {

	public static final Properties getKafkaGeneralProperties() throws IOException {
		final Properties kafkaGenProps = new Properties();
		kafkaGenProps.load(StreamingKafkaConsumerJob.class.getResourceAsStream("/kafka-general.properties"));
		return kafkaGenProps;
	}

	public static final Properties getKafkaConsumerProperties() throws IOException {
		final Properties consumerProps = new Properties();
		consumerProps.load(StreamingKafkaConsumerJob.class.getResourceAsStream("/kafka-flink-consumer.properties"));
		return consumerProps;
	}
}
