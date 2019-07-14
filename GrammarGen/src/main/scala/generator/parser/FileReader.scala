package generator.parser

import scala.io.{ Source, BufferedSource }
import java.lang.NullPointerException

object FileReader {
	def readFileAsBuffSource(filePath: String): Either[NullPointerException, Iterator[String]] = {
		try {
    	Right(Source.fromResource(filePath).getLines)
		}
		catch {
			case e: NullPointerException => Left(e)
		}
	}	

	def readFile(filePath: String): Either[NullPointerException, String] = readFileAsBuffSource(filePath) match {
		case Right(buff) => Right(buff.mkString)
		case Left(e: NullPointerException) => Left(e)
		case Left(e) => Left(e) // should not happen
	}
}