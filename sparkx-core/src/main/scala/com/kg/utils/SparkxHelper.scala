package com.kg.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkxHelper {

  def getSparkSession(env:String): SparkSession = {
    env match {
      case "dev" => {
        val conf: SparkConf = new SparkConf()
          .setAppName("Log2Hudi dev")
          .setMaster("local[*]")
          .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
          .set("spark.sql.hive.metastore.version", "1.2.1")
          .set("spark.sql.cbo.enabled", "true")
          .set("spark.hadoop.dfs.client.block.write.replace-datanode-on-failure.enable", "true")
          .set("spark.hadoop.dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER")
          .set("spark.sql.hive.metastore.jars", "maven")

        val spark: SparkSession = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate()
        spark
      }
      case _ => {
        println("not match env, force exits")
        System.exit(-1)
        null
      }
    }
  }
}