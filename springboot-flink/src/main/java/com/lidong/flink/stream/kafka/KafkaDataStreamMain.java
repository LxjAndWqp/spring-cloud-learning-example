package com.lidong.flink.stream.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

/**
 * 利用 flink kafka 自带的 source 读取 kafka 里面的数据
 */
@Slf4j
public class KafkaDataStreamMain {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
//        props.put("zookeeper.connect", "localhost:2181");
//        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  //key 反序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest"); //value 反序列化

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("metric", new SimpleStringSchema(), props);
        //从最早开始消费
//        consumer.setStartFromEarliest();
        consumer.setStartFromLatest();
        DataStream<String> dataStreamSource = env.addSource(consumer);
        DataStream<String> requtapisMessageVo = dataStreamSource.map(new
                                                                             MapFunction<String, String>() {

                                                                                 @Override
                                                                                 public String map(String s) throws Exception {
                                                                                     log.info("数据{}", s);
                                                                                     return s;
                                                                                 }
                                                                             });

        //4.sink输出
        requtapisMessageVo.print();

        env.execute("Flink add data source");
    }
}
