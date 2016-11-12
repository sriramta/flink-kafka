package org.acme.flink.kafka.data;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

public class NoOpDeserializer implements DataDeserializer {

	@Override
	public File deserializeData(byte[] data, String outDir) {
		final String fileName = UUID.randomUUID().toString();
		final File file = new File(outDir + "/" + fileName);

		try {
			FileUtils.writeByteArrayToFile(file, data);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return file;
	}

}
