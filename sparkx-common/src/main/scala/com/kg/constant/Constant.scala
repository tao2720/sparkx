package com.kg.constant

object Constant {

  //常用数值常量
  val COMMON_ZERO_INT = 0
  val COMMON_ZERO_DOUBLE = 0.0
  val COMMON_ZERO_LONE = 0L

  val COMMON_ONE_INT = 1
  val COMMON_ONE_DOUBLE = 1.0
  val COMMON_ONE_LONE = 1L

  //失败重启策略
  val MAX_FAILUR_RESTART_ACCEPTES = 3
  val MAX_DELAY_INTERVAL = 5  //5秒

  //checkpoint监测点
  val CHECKPOINT_INTERVAL = 10

  //水印插入间隔
  val WATERMARK_INTERVAL = 500  //默认是500毫秒插入一次

  //当前可用线程数 --->获取运行时的最大可用线程
  val MAX_CURRENT_PARALLE = Runtime.getRuntime.availableProcessors()

  //kafka的配置信息
  val KAFKA_CONSUMER_CONFIG_URL = "kafka/kafka-consumer.properties"
  val KAFKA_PRODUCER_CONFIG_URL = "kafka/kafka-producer.properties"
  val KAFKA_GROUP_ID = "group.id"


  //redis的信息
  val REDIS_CONFIG_URL = "redis/redis.properties"
  val REDIS_HOST = "redis_host"
  val REDIS_PORT = "redis_port"
  val REDIS_PASS = "redis_password"
  val REDIS_TIMEOUT = "redis_timeout"
  val REDIS_DB = "redis_db"
  val REDIS_MAX_IDLE = "redis_maxidle"
  val REDIS_MIN_IDLE = "redis_minidle"
  val REDIS_MAX_TOTAL = "redis_maxtotal"


  //es的配置
  val ES_CONFIG_URL = "es/es-config.json"
  val ES_RETRY_NUMBER = 3

  //mysql的配置
  val MYSQL_CONFIG_URL = "jdbc/jdbc.properties"
  val JDBC_DRIVER_KEY = "jdbc.driver"
  val JDBC_URL_KEY = "jdbc.url"
  val JDBC_USER_KEY = "jdbc.user"
  val JDBC_PASSWORD_KEY = "jdbc.password"

  //订单相关字段常量
  val ORDER_TRAVEL_MEMBER_ADULT = "travel_member_adult"
  val ORDER_TRAVEL_MEMBER_BABY = "travel_member_baby"
  val ORDER_USER_REGION = "user_region"
  val ORDER_PRODUCT_FEE = "product_fee"
  val ORDER_PRODUCT_PRICE = "product_price"
  val ORDER_PRODUCT_TRAFFIC_GRADE = "product_traffic_grade"
  val ORDER_TRAVEL_MEMBER_YONGER = "travel_member_yonger"
  val ORDER_PRODUCT_TRAFFIC_TYPE = "product_traffic_type"
  val ORDER_PRODUCT_PUB = "product_pub"
  val ORDER_ORDER_CT = "order_ct"
  val ORDER_KAFKA_ID = "KAFKA_ID"
  val ORDER_USER_ID = "user_id"
  val ORDER_USER_MOBILE = "user_mobile"
  val ORDER_HAS_ACTIVITY = "has_activity"
  val ORDER_PRODUCT_ID = "product_id"
  val ORDER_PRODUCT_TRAFFIC = "product_traffic"
  val ORDER_ORDER_ID= "order_id"
  val ES_ID = "orderID"


  //用户行为日志相关字段
  val LOG_OS = "os"
  val LOG_LONITUDE = "lonitude"
  val LOG_USERREGION = "userRegion"
  val LOG_LATITUDE = "latitude"
  val LOG_EVENT_TYPE = "eventType"
  val LOG_USERID = "userID"
  val LOG_SID = "sid"
  val LOG_MANUFACTURER = "manufacturer"
  val LOG_DURATION = "duration"
  val LOG_CT = "ct"
  val LOG_CARRIER = "carrier"
  val LOG_USERREGIONIP = "userRegionIP"
  val LOG_USERDEVICETYPE = "userDeviceType"
  val LOG_ACTION = "action"
  val LOG_USERDEVICE = "userDevice"
  val LOG_HOTTARGET = "hotTarget"
  val LOG_NETWORKTYPE = "networkType"
  val LOG_EXTS = "exts"
  val LOG_TRAVELSENDTIME = "travelSendTime"
  val LOG_TRAVELTIME = "travelTime"
  val LOG_PRODUCTLEVEL = "productLevel"
  val LOG_TRAVELSEND = "travelSend"
  val LOG_PRODUCTTYPE = "productType"
  val LOG_TARGET_IDS= "targetIDS"
  val LOG_TARGET_ID= "targetID"
  val LOG_EVENT_TARGET_TYPE= "eventTargetType"


  //分桶相关信息
  val BUCKET_CHECK_INTERVAL = 10*60*1000L
  val DATA_FORMAT_YYYYMMDDHH = "yyyyMMddHH"
  val DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmSS"

  //action和事件信息
  val ACTION_INTER = "05"
  val ACTION_H5_PAGE_VIEW = "07"
  val ACTION_NATIVE_PAGE_VIEW = "08"


  val EVENT_VIEW = "01"
  val EVENT_CLICK = "02"
  val EVENT_Slide = "04"

  //异步I/O
  val ASYNC_DBCONN_TIMEOUT = 10000   //测试在调试
  val ASYNC_DBCONN_CAPACITY = 3

  //agg
  val POJO_FIELD_ORDERS = "orderID"
  val POJO_FIELD_FEE = "fee"
  val KEY_CT = "ct"

  //窗口
  val FLINK_WINDOW_MAX_SIZE = 10

  //特殊符号
  val BOTTOM_LINE = "_"

  //cep
  val FLINK_CEP_VIEW_BEGIN  = "pageView"

}
