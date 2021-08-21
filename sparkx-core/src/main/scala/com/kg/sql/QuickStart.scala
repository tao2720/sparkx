package com.kg.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream

object QuickStart {
  def main(args: Array[String]): Unit = {
    //1. 入口类
    val sparkSession = SparkSession.builder().appName("QuickStart")master("local[*]")getOrCreate()

    //1.1 spark sql
    val spark = sparkSession
    //1.2 spark streaming
    val ssc: StreamingContext = new StreamingContext(spark.sparkContext, Seconds(5))

    //1.2 spark streaming
    ssc.checkpoint("file:///d:/data/out")
    //2. 读取数据:001 mi mobile
    val lines:DStream[String] = ssc.socketTextStream("192.168.10.11", 9998)

    //3.切割数据并过滤
    val pairs:DStream[(String, Int)] = lines.map(line => {
      val fields = line.split("\\s+")
      if(fields == null || fields.length != 3) {
        ("", -1)
      }else {
        val brand = fields(1)
        val category = fields(2)
        (s"${category}_${brand}", 1)
      }
    }).filter(t => t._2 != -1)
    //(001_Mi,1)(001_Mi,1)(002_Huawei,1)

    //4. 聚合
    val usb:DStream[(String, Int)] = pairs.updateStateByKey(updateFunc)

    //(001_Mi,2)(002_Huawei,1)

    //5. 遍历
    usb.foreachRDD((rdd, bTime) => {
      if(!rdd.isEmpty()) {
        //5.1 重构数据然后获取到DataFrame
        import spark.implicits._

        val df = rdd.map { case (cb, cnt) => {
          val category = cb.substring(0, cb.indexOf("_"))
          val brand = cb.substring(cb.indexOf("_") + 1)
          (category, brand, cnt)
        }}.toDF("category", "brand", "sales")

        //5.2 创建视图然后查询
        df.createOrReplaceTempView("tmp_category_brand_sales")
        val sql =
          """
            |select
            |t.category,
            |t.brand,
            |t.sales,
            |t.rank
            |from
            |(
            |select
            |category,
            |brand,
            |sales,
            |row_number() over(partition by category order by sales desc) rank
            |from
            |tmp_category_brand_sales
            |) t
            |where
            |rank < 4
            |""".stripMargin
        spark.sql(sql).show()
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }

  /**
   * seq : 表示相同的key聚合之和每一次的业务操作之后的综合
   * option : 当前那次操作的状态
   */
  def updateFunc(seq:Seq[Int], option:Option[Int]):Option[Int] = Option(seq.sum + option.getOrElse(0))
}
