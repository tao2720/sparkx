package com.kg.constant

/**
 * 维度相关的查询信息
 */
object Constant_sql {
  //产品维度
  val PRODUCT_SQL = "select product_id, product_level, product_type, departure_code, des_city_code, toursim_tickets_type from dim_product1"
  val PRODUCT_SCHEMA = "product_id, product_level, product_type, departure_code, des_city_code, toursim_tickets_type"
  val PRODUCT_PK = "product_id"
  val PRODUCT_TABLE_NAME = "dim_product1"

  //酒店维度
  val PUB_SQL: String = "select pub_id, pub_name, pub_star, pub_grade_desc, pub_grade, pub_area_code,pub_address,is_national from dim_pub1"
  val PUB_SCHEMA: String = "pub_id, pub_name, pub_star, pub_grade_desc, pub_grade, pub_area_code,pub_address,is_national"
  val PUB_PK: String = "pub_id"
  val PUB_TABLE_NAME: String = "dim_pub1"

}
