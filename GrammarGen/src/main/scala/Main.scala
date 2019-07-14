import generator.grammar._
import generator.parser._
import generator.grammar.polimorfologik.SentenceGenerator
import io.circe.parser.decode

object Main extends App {

	val terminalGrammar = JsonParser.decodeGrammarFile[TerminalGrammar](
		"Terminals.json",
		FileReader.readFile,
		TerminalGrammar("")
	)

	val nonterminalGrammar = JsonParser.decodeGrammarFile[NonterminalGrammar](
		"Nonterminals.json",
		FileReader.readFile,
		NonterminalGrammar("")
	)

	val tg: Map[String, String] = terminalGrammar match {
		case Right(g: TerminalGrammar) => g.grammar.right.getOrElse(Map())
		case _ => Map()
	}

	val ng: Map[String, List[List[String]]] = nonterminalGrammar match {
		case Right(g: NonterminalGrammar) => g.grammar.right.getOrElse(Map())
		case _ => Map()
	}

	val generator = new TerminalGenerator(tg, ng)

	val poliParser = new PolimorfologikParser(tg)
	val grammarCats = poliParser.groupTokensByCat("polimorfologik-2.1.txt", FileReader.readFileAsBuffSource).right.get

	// Final result
	1 to 10 foreach { _ => println(SentenceGenerator.genSentFromRandSchema(grammarCats, generator)) }
}