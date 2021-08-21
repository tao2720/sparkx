
import org.apache.spark.{SparkConf, SparkContext}


object Demo01 {
  def main(args: Array[String]): Unit = {
    //1. 入口类
    val conf = new SparkConf().setMaster("local[*]").setAppName("QuickStart")
    val sc: SparkContext = new SparkContext(conf) // An existing SparkContext.
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    // Create the DataFrame
//    val df = sqlContext.read.json("sparkx-core/src/main/resources/people.json")
    val df = sqlContext.sql("SELECT * FROM table")


  }
}
