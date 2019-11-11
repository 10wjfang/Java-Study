package com.fang;

import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import scala.Tuple2;

import java.util.*;

/**
 * SparkStreaming整合Kafka
 *
 * @author fwj
 * @date 2019-04-02 17:52
 **/
public class SparkStreamingKafkaTest {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("DirectKafkaWordCount");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(10));
        Map<String, String> params = new HashMap<>();
        params.put("metadata.broker.list", "192.168.10.30:9092,192.168.10.33:9092,192.168.10.32:9092");
        Set<String> topics = new HashSet<>();
        topics.add("topic_1");
        JavaPairInputDStream<String, String> dStream = KafkaUtils.createDirectStream(jssc, String.class, String.class, StringDecoder.class,
                StringDecoder.class, params, topics);
        JavaDStream<String> words = dStream.flatMap(x -> Arrays.asList(x._2.split(" ")).iterator());
        JavaPairDStream<String, Integer> pairs = words.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((i1, i2) -> i1 + i2);
        wordCounts.print();
        jssc.start();
        jssc.awaitTermination();
    }
}
