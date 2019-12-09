package com.lidongexample;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消费counts的topic中的数据，这里我们只是为了演示，
 * 直接输出到控制台，以后我们就可以存储数据库中
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "counts")
    public void listen(ConsumerRecord<?, ?> record) throws Exception {
        String value = (String) record.value();
        log.info("partition = {},topic = {}, offset = {}, value = {}",record.partition(), record.topic(), record.offset(), value);
        WordCount wordCount = JSON.parseObject(value, WordCount.class);
        log.info(wordCount.toString());
    }
}
