package com.lidong;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费者测试
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "ssssss")
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
        log.debug("分区信息----{}",record.partition());
        String value = (String) record.value();
        log.debug("topic = {}, offset = {}, value = {} \n", record.topic(), record.offset(), value);
        WordCount wordCount = JSON.parseObject(value, WordCount.class);
        log.info(wordCount.toString());
    }
}
