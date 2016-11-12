package org.acme.flink.kafka.data;

import java.io.File;

public interface DataDeserializer {

	File deserializeData(byte[] data, String outDir);
}
