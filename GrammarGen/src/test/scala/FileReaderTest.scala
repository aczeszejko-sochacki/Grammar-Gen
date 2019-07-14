package generator.parser

import org.scalatest._
import java.lang.NullPointerException

class FileReaderSpec extends FlatSpec with Matchers {
	"A FileReader.readFile" should "return Left(java.lang.FileNotFoundException) given wrong path" in {
		assertThrows[NullPointerException] {
			throw FileReader.readFile("Wrong path").left.get
		}
	}

	it should "return Right(content) given a correct path to a correct file" in {
		FileReader.readFile("Terminals.json") shouldBe a [Right[_, _]]
		FileReader.readFile("Nonterminals.json").right.get shouldBe a [String]
	}

	"A FileReader.readFileAsBuffSource" should "return Left(java.lang.FileNotFoundException) given wrong path" in {
		assertThrows[NullPointerException] {
			throw FileReader.readFileAsBuffSource("Wrong path").left.get
		}
	}

	it should "return Right(Source.fromResource(path)) iterator given a correct path" in {
		FileReader.readFileAsBuffSource("Terminals.json").right.get shouldBe a [Iterator[String]]
	}
}