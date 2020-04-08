package com.st.process
import org.apache.spark.sql.SparkSession


/**
  * Created with Intellij IDEA.
  * Author: Zhihai
  * Date: 2020-04-07 17:59
  * Email: xianzhihai@gmail.com
  * Description:  
  *
  */

object MainProcess extends App {

  case class Config(masterOpt: Option[String] = None,
                    inputMetaPath: String = "", inputReviewPath: String = "", outputPath: String = "")

  override def main(args: Array[String]): Unit = {
    val p = parser()
    var master = "local"

    // parser.parse returns Option[C]
    p.parse(args, Config()) match {
      case Some(config) => {
        if (config.masterOpt.isDefined){
          master = config.masterOpt.get
        }
        val spark = SparkSession
          .builder()
          .master(master)
          .appName("Musical Instruments Review")
          .config("spark.debug.maxToStringFields", "100")
          .getOrCreate()
        val metaPath = config.inputMetaPath
        val reviewPath = config.inputReviewPath
        val outputPath = config.outputPath

        FilterProcess(spark, metaPath, reviewPath, outputPath)

        spark.close()
      }
      case None =>
    }

  }

  def parser(): scopt.OptionParser[Config] = {
    new scopt.OptionParser[Config]("scopt") {

      head("scopt", "3.x")

      opt[String]("master") optional() action { (masterArg, config) =>
        config.copy(masterOpt = Some(masterArg))
      } text ("master is String describing Spark master - only needed when not running via spark-submit/shell.")
      opt[String]("inputMetaPath") optional () action { (inputMetaPathArg, config) =>
        config.copy(inputMetaPath = inputMetaPathArg)
      } text ("inputMetaPath is String with relative or absolute location of root directory for metadata input file.")

      opt[String]("inputReviewPath") optional () action { (inputReviewPathArg, config) =>
        config.copy(inputReviewPath = inputReviewPathArg)
      } text("inputReviewesPath is String with relative or absolute location of root directory for reviews input file.")

      opt[String]("outputPath") optional () action { (outputPathArg, config) =>
        config.copy(outputPath = outputPathArg)
      } text ("outputPath is String with relative or absolute location of root directory for output file.")

    }
  }
}
