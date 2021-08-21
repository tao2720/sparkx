package com.kg.sql16

import org.apache.spark.{SparkConf, SparkContext}

object Datasets {
  def main(args: Array[String]): Unit = {
    //1. 入口类
    val conf = new SparkConf().setMaster("local[*]").setAppName("Datasets")
    val sc: SparkContext = new SparkContext(conf) // An existing SparkContext.
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    import sqlContext.implicits._
    // Encoders for most common types are automatically provided by importing sqlContext.implicits._
//    val ds = Seq(1, 2, 3).toDS()
//    ds.map(_ + 1).collect() // Returns: Array(2, 3, 4)

    // Encoders are also created for case classes.
    case class Person(name: String, age: Long)
    val ds = Seq(Person("Andy", 32)).toDS()

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name.
    val path = "sparkx-core/src/main/resources/people.json"
    val people = sqlContext.read.json(path).as[Person]

    print(ds)
    print(people)
  }
}
