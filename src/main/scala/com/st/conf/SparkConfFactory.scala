package com.st.conf

import org.apache.spark.SparkConf

/**
  * Created with Intellij IDEA.
  * Author: Zhihai
  * Date: 2020-04-07 14:56
  * Email: xianzhihai@gmail.com
  * Description:  
  *
  */
object SparkConfFactory {

  val MasterKey = "spark.master"

  def apply(appName: String, props: Map[String, String]): SparkConf = {
    val conf = new SparkConf().setAppName(appName)
    for ((key, value) <- props) {
      conf.set(key, value)
    }

    //conf.set(MemoryFraction, MemoryFractionSize)
    //conf.set(MemoryStorageFraction, MemoryStorageFractionSize)
    //conf.set(ExecutorMemoryOverhead, ExecutorMemoryOverheadSize)
    conf
  }

  def setConf(conf : SparkConf, key: String, value: String) : SparkConf = {
    conf.set(key, value)
    conf
  }
}
