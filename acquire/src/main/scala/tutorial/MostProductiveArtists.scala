package tutorial

import cascading.tuple.Fields
import com.twitter.scalding._
import com.twitter.scalding.typed.TDsl._

class MostProductiveArtists(args : Args) extends Job(args) with FieldConversions {
  val artistsBySongs= TypedH5[(String, String, String, String)]("data/unique_tracks.txt")
      .read
      .toTypedPipe[(String, String, String, String)](Fields.ALL)
      .groupBy{ track => track._3 }
      .count{ _ => true }
      .toTypedPipe
      .groupAll
      .sortBy{ - _._2 }
      .values

  artistsBySongs.write(TypedTsv[(String, Long)]("results/top-artists.tsv"))
}

object MostProductiveArtists extends JobRunner[MostProductiveArtists]

