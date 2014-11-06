package tutorial


import com.twitter.scalding.{Tool, Job}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.util.ToolRunner


class JobRunner[ImplementationClass <: Job : Manifest] {
  def main(args : Array[String]) {
    ToolRunner.run(new Configuration, new Tool,
      classManifest[ImplementationClass].erasure.getCanonicalName +: "--local" +: args)
  }
}
