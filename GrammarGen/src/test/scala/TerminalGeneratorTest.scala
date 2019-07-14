package generator.grammar

import org.scalatest._
import java.lang.IllegalArgumentException

class TerminalGeneratorSpec extends FlatSpec with Matchers {
	val mockTerminalGrammar: TerminalGrammarT = Map(
		"A" -> "a",
		"B" -> "b"
	)

	val mockNonterminalGrammar: NonterminalGrammarT = Map(
		"S" -> List(
			List("A", "A"),
			List("N", "A")
		),
		"N" -> List(
			List("A"),
			List("A", "A")
		)
	)

	val terminalGenerator = new TerminalGenerator(mockTerminalGrammar, mockNonterminalGrammar)

	"A TerminalGenerator.expandToTerminals" should "return List[Terminal] given nonterminal start symbol" in {
		terminalGenerator.expandToTerminals(Nonterminal("S")).foreach(_ shouldBe a [Terminal])
		terminalGenerator.expandToTerminals(Nonterminal("N")).foreach(_ shouldBe a [Terminal])
	}

	it should "throw Nonterminal grammar must contain start symbol" in {
		assertThrows[IllegalArgumentException] {
			terminalGenerator.expandToTerminals(Nonterminal("A"))
		}
	}
}