package generator.parser

import org.scalatest._
import org.scalamock.scalatest.MockFactory
import java.lang.{ NullPointerException, IllegalArgumentException }

class JsonParserSpec extends FlatSpec with MockFactory with Matchers {
	val readFileMock = mockFunction[String, Either[java.lang.NullPointerException, String]]

	def grammarPolyHelper[T <: Grammar](filePath: String, kind: T) =
		JsonParser.decodeGrammarFile[T](filePath, readFileMock, kind)

	"A JsonParser.decodeGrammarFile" should "return terminal grammar class given terminal kind" in {
		readFileMock expects "/Terminals.json" returning Right("content") once

		assertResult(Right(TerminalGrammar("content"))) {
			grammarPolyHelper[TerminalGrammar]("/Terminals.json", TerminalGrammar(""))
		}
	}

	it should "return nonterminal grammar class given nonterminal kind" in {
		readFileMock expects "/Nonterminals.json" returning Right("content") once

		assertResult(Right(NonterminalGrammar("content"))) {
			grammarPolyHelper[NonterminalGrammar]("/Nonterminals.json", NonterminalGrammar(""))
		}
	}
}