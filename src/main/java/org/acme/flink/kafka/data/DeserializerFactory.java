package org.acme.flink.kafka.data;

import java.io.File;

public class DeserializerFactory {

	public static File deserializeData(byte[] data, String compressionType, String outDir) {
		if ("lz4".equals(compressionType)) {
			return new Lz4Deserializer().deserializeData(data, outDir);
		} else if ("none".equals(compressionType)) {
			return new NoOpDeserializer().deserializeData(data, outDir);
		} else {
			return null;
		}
	}
}
