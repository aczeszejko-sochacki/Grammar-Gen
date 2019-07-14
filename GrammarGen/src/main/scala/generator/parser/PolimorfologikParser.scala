package generator.parser

import java.lang.NullPointerException
import scala.util.matching.Regex

class PolimorfologikParser(terminalGrammar: TerminalGrammarT) {
	val tg = terminalGrammar // alias

	val tokensByCatIm: Map[String, List[String]] = tg.values.map(cat => (cat, List())) toMap
	var tokensByCat: TokensCatT = collection.mutable.Map(tokensByCatIm.toSeq: _*)

	val catRegex = tokensByCat.keys.map(_.r)

	def groupTokensByCat(
		filePath: String,
		readFileAsBuffSource: String => Either[NullPointerException, Iterator[String]]
	): Either[NullPointerException, TokensCatT] = readFileAsBuffSource(filePath) match {

			case Right(iter) => {
				for (line <- iter) catRegex map { r =>
					val Array(_, base, cats) = line.split(';')

					r.findFirstMatchIn(cats) match {
						case Some(_) => tokensByCat(r.toString) = base :: tokensByCat(r.toString)
						case None =>
					}
				}

				Right(tokensByCat)
			}
			case Left(e: NullPointerException) => Left(e)
			case Left(e) => Left(e) // should not happen
	}
}