package codec

trait Codec[A]:
  self =>
  def encode(value: A): String
  def decode(value: String): A
  final def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B]:
    override def encode(value: B): String = self.encode(enc(value))
    override def decode(value: String): B = dec(self.decode(value))

object CodecInstances:
  given stringCodec: Codec[String] with
    def encode(value: String): String = value
    def decode(value: String): String = value
  
  given doubleCodec: Codec[Double] = stringCodec.imap(_.toDouble, _.toString)
  given intCodec: Codec[Int] = stringCodec.imap(_.toInt, _.toString)
  
object CodecSyntax:
  extension [A](value: A)
    def encode(using c: Codec[A]): String = c.encode(value)
    
  extension (value: String)
    def decode[A](using c: Codec[A]): A = c.decode(value)
