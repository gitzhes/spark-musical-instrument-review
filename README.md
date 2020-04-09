## Program: spark-musical-instrument-review

# Overview

The spark program contains code for Question 1 on finding the top 10 products based on the number of unique user reviews by product.

| Scala Version | Spark Version |
|---|---|
|  2.11.12 | 2.4.X |

## Build the project
>#build "fat" jar with classes and all dependencies under
>
>#"target/scala-2.11/spark-musical-instrument-review-assembly-0.1.jar"
>
>#go to project folder
>
>cd spark-musical-instrument-review
>
>sbt assembly
>
>sbt compile
>
>sbt packages
>
>#compiled jar at "target/scala-2.11/spark-musical-instrument-review_2.11-0.1.jar

## Run the program
> There are 3 required arguments to run spark-submit \
> 1. --inputMetaPath <Full path to metadata\>
> 2. --inputReviewPath <Full path to review data\>
> 3. --outputPath <Full path to output json directory\>
>
>./bin/spark-submit \
  --class com.st.process.MainProcess  \
  --master local\[*] \
  --jars /path/to/spark-musical-instrument-review-assembly-0.1.jar \
  /path/to/spark-musical-instrument-review_2.11-0.1.jar \
  --inputMetaPath /path/to/meta_Musical_Instruments.json \
  --inputReviewPath /path/to/reviews_Musical_Instruments_5.json \
  --outputPath /path/to/outputDirectory

#### Please verify the result json at output directory 