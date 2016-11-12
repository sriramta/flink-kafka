package org.acme.flink.kafka.support;

import java.io.File;
import java.io.IOException;

import org.acme.flink.kafka.data.DeserializerFactory;
import org.acme.flink.kafka.util.PropertiesUtil;
import org.apache.flink.streaming.util.serialization.AbstractDeserializationSchema;

public class FileDeserializationSchema extends AbstractDeserializationSchema<File> {

	private String outDir;
	private String compressionType;

	public FileDeserializationSchema() {
		try {
			outDir = PropertiesUtil.getKafkaGeneralProperties().getProperty("arc.file.out.dir");
			compressionType = PropertiesUtil.getKafkaGeneralProperties().getProperty("file.compression.type");
		} catch (final IOException e) {
			outDir = "";
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 7503943071991293067L;

	@Override
	public File deserialize(byte[] message) throws IOException {
		return DeserializerFactory.deserializeData(message, compressionType, outDir);
	}

}
