package com.lidong;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 测试kafka生产者
 */
@RestController
@RequestMapping("kafka")
@Slf4j
public class TestKafkaProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    public String send(String msg){

        for (int i = 0; i < 1000 ; i++) {
            /**
             * ProducerRecord  发送给kafka broker 的是一个的key/value 值对
             *
             *内部数据结构：
             *-- Topic -- PartitionID -- Key -- Value
             *
             * ProducerRecord 的发送逻辑:
             * <1> 若指定Partition ID,则PR被发送至指定Partition
             * <2> 若未指定Partition ID,但指定了Key, PR会按照hasy(key)发送至对应Partition
             * <3> 若既未指定Partition ID也没指定Key，PR会按照round-robin模式发送到每个Partition
             * <4> 若同时指定了Partition ID和Key, PR只会发送到指定的Partition (Key不起作用，代码逻辑决定)
             *
             */
//            String data = msg+" 1751 1464116 cart "+System.currentTimeMillis()/1000;
            /**
             * "sid": "t_xxsfdsad",
             * 	"s_value": "85.5",
             * 	"s_ts": "1515228763"
             */
           String data = "";
            if (i%5==0){
                data = "lidongx";
            }else if(i%5==1){
                data = "lidongxx";
            }else if(i%5==2){
                data = "wwwwxx lidongxx";
            }else if(i%5==3){
                data = "wwww shitou";
            }else if(i%5==4){
                data = "shitou huge";
            }
            ListenableFuture<SendResult<String, String>> test_topic = kafkaTemplate.send("words", data);
            test_topic.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.error("发送成功",ex);
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("发送成功",JSON.toJSON(result));
                }
            });
        }


        return "success";
    }

}
