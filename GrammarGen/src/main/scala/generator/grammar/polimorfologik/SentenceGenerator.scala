package generator.grammar.polimorfologik

import scala.util.Random
import generator.grammar._

object SentenceGenerator {
	def genSentFromRandSchema(tokensByCat: TokensCatT, schemaGen: TerminalGenerator): String = {
		val schema = schemaGen.expandToTerminals(Nonterminal("S")).map(_.symb)

		schema map { cat =>
			val tokenIndex = Random.nextInt(tokensByCat(cat).length)
			tokensByCat(cat)(tokenIndex)
		} mkString(" ") capitalize
	}
}