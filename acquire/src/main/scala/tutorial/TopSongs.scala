package tutorial


import cascading.tuple.Fields
import com.twitter.scalding.{TypedTsv, FieldConversions, Job, Args}
import com.twitter.scalding.typed.TDsl._

class TopSongs(args : Args) extends Job(args) with FieldConversions
{
  val topSongs= TypedTsv[(String, String, Long)]("data/train_triplets.txt").read
    .toTypedPipe[(String, String, Long)](Fields.ALL)
    .groupBy{ _._2 }
    .count{ _ => true }
    .toTypedPipe
    .groupAll
    .sortBy{ -_._2 }
    .take(1000)
    .values

  topSongs.write(TypedTsv[(String, Long)]("results/top-songs.tsv"))
}

object TopSongs extends JobRunner[TopSongs]

