package com.lidong.flink.stream.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.sql.Timestamp;
import java.util.Properties;

/**
 * 利用 flink kafka 自带的 source 读取 kafka 里面的数据
 */
@Slf4j
public class SensorDataKafkaDataStreamMainSQL {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
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
        DataStream<String> stream = env.addSource(consumer);

        DataStream<Tuple3<String, String, String>> map = stream.map(new MapFunction<String, Tuple3<String, String, String>>() {

            @Override
            public Tuple3<String, String, String> map(String value) throws Exception {
                String[] split = value.split(" ");
                return new Tuple3<String, String, String>(split[0],split[1],split[2]);
            }
        });

        map.print(); //打印流数据


        //注册为user表
        tableEnv.registerDataStream("s_sensor_data", map, "s_id,s_ts,s_value");

        //执行sql查询     滚动窗口 10秒    计算10秒窗口内用户点击次数
        Table sqlQuery = tableEnv.sqlQuery("SELECT * FROM s_sensor_data ");


        //Table 转化为 DataStream
        DataStream<Tuple3<String, String, String>> appendStream = tableEnv.toAppendStream(sqlQuery,Types.TUPLE(Types.STRING,Types.STRING,Types.STRING));
        appendStream.print();

        env.execute("userPv from Kafka");
    }
}
