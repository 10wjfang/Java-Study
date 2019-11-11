package com.fang;

import javafx.util.Duration;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * 第一个Spark程序
 *
 * @author fwj
 * @date 2019-03-29 16:27
 **/
public class SparkTest {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            System.err.println("Usage: JavaNetworkWordCount <hostname> <port>");
            System.exit(1);
        }
        // 1、创建JavaStreamingContext对象
        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));
        // 2、使用JavaStreamingContext创建DStream
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream(args[0], Integer.parseInt(args[1]));
        // 3、拆分单词
        JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator());
        // 4、统计词频
        JavaPairDStream<String, Integer> pairs = words.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey((i1, i2) -> i1 + i2);
        wordCounts.print();
        // 5、启动
        jssc.start();
        jssc.awaitTermination();
    }
}
