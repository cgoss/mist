import mist.api._
import mist.api.DefaultEncoders._
import org.apache.spark.SparkContext

object SimpleContextV2 extends MistJob[Array[Int]] {

  import mist.api.args.WithArgsScala.ArgMagnet._

  override def defineJob = {
    withArgs(
      arg[Seq[Int]]("numbers"),
      arg[Int]("multiplier", 2)
    ).withMistExtras
     .onSparkContext((nums: Seq[Int], mult: Int, extras: MistExtras, sc: SparkContext) => {

       import extras._

       logger.info(s"Heello from $jobId")
       sc.parallelize(nums).map(_ * mult).collect()
     })
  }

}
