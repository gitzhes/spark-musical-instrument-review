package com.st.process

import scala.io.Source
import java.io._
import org.apache.spark.sql.SparkSession

/**
  * Created with Intellij IDEA.
  * Author: Zhihai
  * Date: 2020-04-07 20:28
  * Email: xianzhihai@gmail.com
  * Description:  
  *
  */
object FilterProcess {

  def apply(spark: SparkSession, metaPath: String, reviewPath: String, outputPath: String) : Unit ={
    import spark.implicits._
    val metaDF = spark.read.option("allowBackslashEscapingAnyCharacter", "true")
      .option("allowUnquotedControlChars", "true").json(metaPath)
    val reviewDF=spark.read.json(reviewPath)
    val mdf = metaDF.select("asin", "price").distinct

    val agg_df = reviewDF.select("asin", "reviewerID").distinct.groupBy("asin")
      .count.sort($"count".desc).limit(10).withColumnRenamed("asin", "product_id")

    val final_df = agg_df.join(mdf, agg_df("product_id") === mdf("asin"), "left")

    final_df.select("product_id", "count", "price").write.json(outputPath)

  }

}
