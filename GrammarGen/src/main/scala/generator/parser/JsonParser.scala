package generator.parser

import io.circe.parser.decode
import java.lang.NullPointerException

sealed trait Grammar

case class NonterminalGrammar(grammarJson: String) extends Grammar {
	val grammar: Either[io.circe.Error, NonterminalGrammarT] = decode[NonterminalGrammarT](grammarJson)
}

case class TerminalGrammar(grammarJson: String) extends Grammar {
	val grammar: Either[io.circe.Error, TerminalGrammarT] = decode[TerminalGrammarT](grammarJson)
}

object JsonParser {
	def decodeGrammarFile[T <: Grammar](
		filePath: String,
		readFile: String => Either[NullPointerException, String],
		kind: T): Either[NullPointerException, Grammar] = {

		// Could not use polymorphic parameter in a better way, because
		// io.circe.parser.decode needs an explicit type
		(readFile(filePath), kind) match {
			case (Right(content: String), TerminalGrammar(_)) => Right(TerminalGrammar(content))
			case (Right(content: String), NonterminalGrammar(_)) => Right(NonterminalGrammar(content))
			case (Left(e: NullPointerException), _) => Left(e)
			case _ => Left(new NullPointerException) // should not happen
		}
	}
}