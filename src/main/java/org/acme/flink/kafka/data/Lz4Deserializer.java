package org.acme.flink.kafka.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import net.jpountz.lz4.LZ4BlockOutputStream;

public class Lz4Deserializer implements DataDeserializer {

	@Override
	public File deserializeData(byte[] data, String outDir) {
		final byte[] buf = new byte[2048];
		final String fileName = UUID.randomUUID().toString();
		final File file = new File(outDir + "/" + fileName);

		try (ByteArrayInputStream in = new ByteArrayInputStream(data);
				final FileOutputStream fos = new FileOutputStream(file);
				final LZ4BlockOutputStream lz4Out = new LZ4BlockOutputStream(fos);) {

			int len;
			while ((len = in.read(buf)) > 0) {
				lz4Out.write(buf, 0, len);
			}
			lz4Out.flush();
			fos.flush();

		} catch (final IOException e) {
			e.printStackTrace();
		}

		return file;
	}

}
