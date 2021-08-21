package com.kg.reader

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object HdfsReader {
  def main(args: Array[String]): Unit = {
    //2. 获取核心类
    val streamContext: StreamingContext = new StreamingContext(new SparkConf()
      .setAppName("HdfsReader").setMaster("local[*]"), Seconds(5))

    //3. 业务:单词统计
//    val lines: DStream[String] = streamContext.textFileStream("hdfs://192.168.10.11:9870/data")

    val lines: DStream[String] = streamContext.textFileStream("file:///D:\\data")

    val retDStream:DStream[(String, Int)] = lines.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_+_)
    retDStream.print
    //4. 为了执行的流式计算，必须要调用start来启动
    streamContext.start()
    //5. 为了不至于start启动程序结束，必须要调用awaitTermination方法等待程序业务完成之后调用stop方法结束程序，或者异常
    streamContext.awaitTermination()
  }

}
