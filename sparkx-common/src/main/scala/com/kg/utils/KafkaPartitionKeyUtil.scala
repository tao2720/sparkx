package com.kg.utils

import java.util

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

/**
 * 自定义kafka的分区器
 */
class KafkaPartitionKeyUtil extends Partitioner{
  override def configure(map: util.Map[String, _]): Unit = {}

  //获取分区编号 --- 根据key的hash值进行%总的分区数
  override def partition(topic: String, o: Any, bytes: Array[Byte], o1: Any, bytes1: Array[Byte], cluster: Cluster): Int = {
    //获取总的分区数
    val total_partitions = cluster.availablePartitionsForTopic(topic).size()
    var partition = 0
    if(bytes != null){
      //获取key
      val key:String = new String(bytes)
      partition = key.hashCode % total_partitions
    }
    partition
  }

  override def close(): Unit = {}
}
