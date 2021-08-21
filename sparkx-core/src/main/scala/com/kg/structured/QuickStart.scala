package com.kg.structured

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{Dataset, SparkSession}

object QuickStart {
  def main(args: Array[String]): Unit = {
    //1. 获取spark session
    val spark: SparkSession = SparkSession.builder()
      .appName("QuickStart")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR") // 设置日志级别避免影响查看控制台的日志
    import spark.implicits._

    //2. 通过spark sql获取到结构流
    val ds: Dataset[String] = spark.readStream
      .format("socket")
      .option("host", "192.168.10.10")
      .option("port", 9999)
      .load()
      .as[String] // 默认dataStreaming返回的是dataframe,我们将其转换dataset

    //3. 写业务
    val wordcountDS: Dataset[(String, Long)] = ds.flatMap(_.split("\\s+"))
      .map((_, 1))
      .groupByKey(_._1)
      .count()

    //4. 开启结构流，线程等待
    wordcountDS.writeStream
      .outputMode(OutputMode.Complete()) // 统计全局结果，而不是单个批次
      .format("console") // 将结果输出到控制台
      .start() // 开始结构流
      .awaitTermination() //阻塞线程
  }
}
