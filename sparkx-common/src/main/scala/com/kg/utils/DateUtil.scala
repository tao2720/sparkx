package com.kg.utils

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId}
import java.util.{Date, Locale}

/**
 * 时间工具类的封装
 */
object DateUtil {
  val PATTERN_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd")

  //将time时间戳转换成指定pattern的时间字符串
  def dateFormat(time: Long, pattern: String): String = {
    if (time <= 0 || null == pattern) {
      return null
    }
    val datetime = LocalDateTime.ofInstant(new Date(time).toInstant, ZoneId.systemDefault())
    datetime.format(DateTimeFormatter.ofPattern(pattern))
  }

  //将date类型时间转换成时间字符串
  def dateFormat(date: Date): String = {
    if (null == date) {
      return null
    }
    val datetime = LocalDateTime.ofInstant(date.toInstant, ZoneId.systemDefault())
    datetime.format(PATTERN_YYYYMMDD)
  }

  /**
   * 将时间差转换成对应的时区的字符串，，用于结果打入es的ct字段
   *
   * @param tm
   * @return
   */
  def tranTimeToString(tm: Long) = {
    val fm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
    val tim = fm.format(new Date(tm))
    tim
  }


  //测试
  def main(args: Array[String]): Unit = {
    print(tranTimeToString(1591328158000l))
    println()
    print(dateFormat(new Date()))
    println()
    println(dateFormat(1591328158000l, "yyyyMMdd HH"))
  }
}
