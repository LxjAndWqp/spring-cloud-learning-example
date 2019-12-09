package com.lidong.flink.stream.rabbitmq;


import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;

/**
 * 从 rabbitmq 读取数据
 */
@Slf4j
public class RabbitmqDataStreamMain {

    public static void main(String[] args) throws Exception {

            //1.获取flink的运行环境
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
                    .setHost("127.0.0.1")   //地址
                    .setPort(5672)
                    .setUserName("root")     //别用默认的，自己创建一个用户，注意用户权限
                    .setPassword("123456")
                    .setVirtualHost("/")
                    .build();

            //2.连接socket获取输入的数据(数据源Data Source)
            //2.1. rabbitmq连接的配置，2.rabbitmq的队列名，消费的队列名
            DataStream<String> dataStreamSource = env.addSource(new RMQSource<String>(connectionConfig,
                    "zhisheng",true, new SimpleStringSchema()));
            //3.数据转换
            //MapFunction:第一个参数是你接收的数据的类型
            //MapFunction:第二个参数是返回数据的类型
            DataStream<String> requtapisMessageVo = dataStreamSource.map(new
                          MapFunction<String, String>() {

                              @Override
                              public String map(String s) throws Exception {
                                  log.info("数据{}",s);
                                  return s+"--------------------";
                              }
                          });

            //4.sink输出
            requtapisMessageVo.print();
            //5.这一行代码一定要实现，否则程序不执行
            env.execute("Socket window coun1t");
        }

}
