package com.kg.sql16

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream

object QuickStart {
  def main(args: Array[String]): Unit = {
    //1. 入口类
    val conf = new SparkConf().setMaster("local[*]").setAppName("QuickStart")
    val sc: SparkContext = new SparkContext(conf) // An existing SparkContext.
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    // Create the DataFrame
    val df = sqlContext.read.json("sparkx-core/src/main/resources/people.json")
//    val df = sqlContext.sql("SELECT * FROM table")

    // Show the content of the DataFrame
    df.show()
    // age  name
    // null Michael
    // 30   Andy
    // 19   Justin

    // Print the schema in a tree format
    df.printSchema()
    // root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)

    // Select only the "name" column
    df.select("name").show()
    // name
    // Michael
    // Andy
    // Justin

    // Select everybody, but increment the age by 1
    df.select(df("name"), df("age") + 1).show()
    // name    (age + 1)
    // Michael null
    // Andy    31
    // Justin  20

    // Select people older than 21
    df.filter(df("age") > 21).show()
    // age name
    // 30  Andy

    // Count people by age
    df.groupBy("age").count().show()
    // age  count
    // null 1
    // 19   1
    // 30   1



  }
}
