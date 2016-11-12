package org.acme.flink.kafka;

import java.io.File;

import org.acme.flink.kafka.support.FileDeserializationSchema;
import org.acme.flink.kafka.util.PropertiesUtil;
import org.apache.flink.streaming.api.datastream.DataStream;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * For a full example of a Flink Streaming Job, see the
 * SocketTextStreamWordCount.java file in the same package/directory or have a
 * look at the website.
 *
 * You can also generate a .jar file that you can submit on your Flink cluster.
 * Just type mvn clean package in the projects root directory. You will find the
 * jar in target/flink-kafka-0.0.1-SNAPSHOT.jar From the CLI you can then run
 * ./bin/flink run -c org.acme.flink.kafka.StreamingJob
 * target/flink-kafka-0.0.1-SNAPSHOT.jar
 *
 * For more information on the CLI see:
 *
 * http://flink.apache.org/docs/latest/apis/cli.html
 */
public class StreamingKafkaConsumerJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.enableCheckpointing(5000);

		final FlinkKafkaConsumer010<File> fileStreamConsumer = new FlinkKafkaConsumer010<>("acme-test",
				new FileDeserializationSchema(), PropertiesUtil.getKafkaConsumerProperties());
		final DataStream<File> fileStream = env.addSource(fileStreamConsumer);
		fileStream.print();

		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
