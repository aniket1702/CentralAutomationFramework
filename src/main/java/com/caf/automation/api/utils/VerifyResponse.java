package com.caf.automation.api.utils;

import com.caf.automation.api.kafka.consumer.KafkaEventConsumer;
import com.caf.automation.api.kafka.producer.KafkaEventProducer;
import com.caf.automation.config.ConfigFactory;
import com.caf.automation.loggers.LogType;
import com.caf.automation.loggers.LogUtils;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

public final class VerifyResponse {
    private VerifyResponse(){}

    public static void statusCode(Response response, int expectedStatusCode)
    {
        int actualStatusCode = response.statusCode();

        try
        {
            if (response.getBody()!=null)
            {
                Assert.assertEquals(actualStatusCode,expectedStatusCode);
                if (actualStatusCode==expectedStatusCode)
                {
                    LogUtils.log(LogType.PASS,"ACTUAL_STATUS_CODE: "+actualStatusCode+"\nEXPECTED_STATUS_CODE: "+expectedStatusCode);
                }
                else {
                    LogUtils.log(LogType.FAIL,"ACTUAL_STATUS_CODE: "+actualStatusCode+"\nEXPECTED_STATUS_CODE: "+expectedStatusCode);
                }

            }
        }
        catch (AssertionError | Exception err)
        {
            LogUtils.log(LogType.FAIL, err.getMessage());
        }
    }


    public static void printResponse(Response response)
    {
        try{
            if(response!=null)
            {
                LogUtils.log(LogType.PASS,response.asPrettyString());
            }
            else
            {
                LogUtils.log(LogType.FAIL,"Response is null.");
            }
        }
        catch (AssertionError | Exception err)
        {
            LogUtils.log(LogType.FAIL, err.getMessage());
        }
    }

    public static void responseKey(String actualResult, Response response, String path)
    {
        try
        {
            String expectedResult = null;
            if (response!=null)
            {
                expectedResult = response.jsonPath().getJsonObject(path);
                Assert.assertEquals(actualResult,expectedResult);
                LogUtils.log(LogType.PASS,"ACTUAL_RESPONSE: "+actualResult+"\nEXPECTED_RESULT: "+expectedResult);
            }
            else
            {
                LogUtils.log(LogType.FAIL,"ACTUAL_RESPONSE: "+actualResult+"\nEXPECTED_RESULT: "+expectedResult);
            }
        }
        catch (AssertionError | Exception err)
        {
            LogUtils.log(LogType.FAIL, err.getMessage());
        }

    }


    public static void verifyKafkaEvents(KafkaEventProducer producer, KafkaEventConsumer consumer, String payload)
    {
        try
        {
            producer.sendMessage(ConfigFactory.getConfig().kafkaTopic(),ConfigFactory.getConfig().kafkaKey(),payload);
            LogUtils.log(LogType.PASS, "Message sent to Kafka: " + payload);

            consumer.consumeMessages(ConfigFactory.getConfig().kafkaTopic());
            List<String> kafkaMessages = consumer.getConsumedMessages();

            // Log and validate consumed Kafka messages
            if (kafkaMessages.isEmpty()) {
                LogUtils.log(LogType.FAIL, "No messages consumed from Kafka topic: " + ConfigFactory.getConfig().kafkaTopic());
            } else {
                kafkaMessages.forEach(message -> LogUtils.log(LogType.PASS, "Consumed Kafka message: " + message));
            }

        }
        catch (Exception e) {
            LogUtils.log(LogType.FAIL, "Error while handling Kafka events: " + e.getMessage());
        }



    }
}
