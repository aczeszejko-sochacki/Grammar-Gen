package generator.grammar.polimorfologik

import org.scalatest._
import generator.grammar._
import org.scalamock.scalatest.MockFactory

class SentenceGeneratorSpec extends FlatSpec with MockFactory {
	val mockTokensByCat: TokensCatT = scala.collection.mutable.Map(
		"a" -> List("1", "2"),
		"b" -> List("3", "4")
	)

	// val mockSchemaGen = mock[TerminalGenerator]
	// mockSchemaGen.expandToTerminals(_) returns List((Terminal("a"), Terminal("b")))

	"A SentenceGenrator.genSentFromRandSchema" should "return a sentence (string)" in {

	}
}