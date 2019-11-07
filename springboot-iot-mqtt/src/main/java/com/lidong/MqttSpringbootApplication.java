package com.lidong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MqttSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttSpringbootApplication.class, args);

		test();
	}

	private static void test(){
		MqttPushClient.MQTT_HOST = "tcp://127.0.0.1:61613";
		MqttPushClient.MQTT_CLIENTID = "e39ba416a63d43a0a1fc907d1e697387";
		MqttPushClient.MQTT_USERNAME = "admin";
		MqttPushClient.MQTT_PASSWORD = "password";
		MqttPushClient client = MqttPushClient.getInstance();
		client.subscribe("iot");
	}
}
