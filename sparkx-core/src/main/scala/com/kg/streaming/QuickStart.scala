package com.kg.streaming

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3

// Create a local StreamingContext with two working thread and batch interval of 1 second.
// The master requires 2 cores to prevent from a starvation scenario.

object QuickStart {
  def main(args: Array[String]): Unit = {

//    1. 参数过滤
//    if (args == null || args.length != 2) {
//      println(
//        """
//          |Useage : <hostname> <port>
//          |""".stripMargin)
//      System.exit(-1)
//    }

//    var Array(hostname, port) = args

    val conf = new SparkConf().setMaster("local[*]").setAppName("QuickStart")
    val ssc = new StreamingContext(conf, Seconds(5))

    // Create a DStream that will connect to hostname:port, like localhost:9999
    val lines = ssc.socketTextStream("192.168.10.11", 9998)
    // Split each line into words
    val words = lines.flatMap(_.split(" "))
    import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3
    // Count each word in each batch
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()

    ssc.start()             // Start the computation
    ssc.awaitTermination()  // Wait for the computation to terminate

  }
}
