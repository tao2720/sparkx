package com.kg.conf

import scopt.OptionParser

case class Config(
                   env:String = "",
                   brokerList:String = "",
                   sourceTopic:String = "",
                   trigger:String = "300",
                   checkpointDir:String = "",
                   path:String = "",
                   hudiEventBasePath:String = "",
                   hudiUserBasePath:String = "",
                   tableType:String = "COW",
                   syncDB:String = "",
                   syncJDBCUrl:String = "",
                   syncJDBCUsername:String = "hive"
                 )

object Config {


  def parseConfig(obj: Object, args: Array[String]):Config = {
    //1. 类名
    val programName: String = obj.getClass.getSimpleName.replaceAll("\\$", "")
    //2. 选项解析器
    val parser: OptionParser[Config] = new scopt.OptionParser[Config]("spark ss " + programName) {
      head(programName, "v1.0") // 添加usage
      opt[String]('e', "env").required().action((x, config) => config.copy(env = x)).text("env: dev or prod")
      opt[String]('b', "brokerList").required().action((x, config) => config.copy(brokerList = x)).text("kafka broker list,sep comma")
      opt[String]('t', "sourceTopic").required().action((x, config) => config.copy(sourceTopic = x)).text("kafka topic")
      programName match {
        case "Log2Console" =>
        case "Log2HDFS" =>
        case "Log2Hudi" =>
          opt[String]('i', "trigger").required().action((x, config) => config.copy(trigger = x)).text("default 300 second,streaming trigger interval")
          opt[String]('c', "checkpointDir").required().action((x, config) => config.copy(checkpointDir = x)).text("hdfs dir which used to save checkpoint")
          opt[String]('g', "hudiEventBasePath").required().action((x, config) => config.copy(hudiEventBasePath = x)).text("hudi event table hdfs base path")
          opt[String]('u', "hudiUserBasePath").required().action((x, config) => config.copy(hudiUserBasePath = x)).text("hudi user table  hdfs base path")
          opt[String]('s', "syncDB").required().action((x, config) => config.copy(syncDB = x)).text("hudi sync hive db")
          opt[String]('y', "tableType").required().action((x, config) => config.copy(tableType = x)).text("hudi table type MOR or COW. default COW")
          opt[String]('r', "syncJDBCUrl").required().action((x, config) => config.copy(syncJDBCUrl = x)).text("hive server2 jdbc, eg. jdbc:hive2://172.17.106.165:10000")
          opt[String]('n', "syncJDBCUsername").required().action((x, config) => config.copy(syncJDBCUsername = x)).text("hive server2 jdbc username, default: hive")
      }
    }
    //3. 解析
    parser.parse(args, Config()) match {
      case Some(conf) => conf
      case None => {
        println("cannot parse params")
        System.exit(-1)
        null
      }
    }
  }
}