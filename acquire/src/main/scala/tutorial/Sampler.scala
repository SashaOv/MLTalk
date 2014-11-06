package tutorial


import cascading.pipe.Pipe
import cascading.tuple.Fields
import com.twitter.scalding._
import com.twitter.scalding.typed.TDsl._

class Sampler(args : Args) extends Job(args) with FieldConversions
{
  val MetadataSchema= List('track_id, 'title, 'song_id, 'release, 'artist_id, 'artist_mbid, 'artist_name,
    'duration, 'artist_familiarity, 'artist_hotttnesss, 'year, 'track_7digitalid, 'shs_perf, 'shs_work)

  val metadata= Csv("../data/track_metadata.csv", fields= MetadataSchema).read

  val TripletsSchema= List('user_id, 'song_id, 'count)

  val sampledTriplets= Tsv("../data/train_triplets.txt", fields= TripletsSchema).read
      .filter('user_id) { id: String => isInSample(id) }

  val numberedSongs= mapToNumber(metadata, 'song_id, 'sid)
  val numberedUsers= mapToNumber(sampledTriplets, 'user_id, 'uid)

  val numberedTriplets= sampledTriplets.joinWithTiny('user_id -> 'user_id, numberedUsers)
      .joinWithTiny('song_id -> 'song_id, numberedSongs)

//  numberedTriplets.write(Csv("results/numbered-triplets.csv", writeHeader= true))

  val sampled= metadata.joinWithSmaller('song_id -> 'song_id, numberedTriplets)

  sampled.write(Csv("../results/sampled.csv", writeHeader= true))

  def isInSample(id: String)=  Math.abs(id.hashCode) < Int.MaxValue / 1024

  def mapToNumber(pipe: Pipe, idFields: Fields, numberedId: Fields) =
    pipe.project(idFields)
    .distinct(idFields)
    .toTypedPipe[String](Fields.ALL)
    .groupAll
    .mapValueStream{ _.zipWithIndex }
    .values
    .toPipe(Fields.join(idFields, numberedId))

}

object Sampler extends JobRunner[Sampler]

