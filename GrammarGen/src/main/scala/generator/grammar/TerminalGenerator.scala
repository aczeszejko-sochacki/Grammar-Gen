package generator.grammar

import scala.util.Random

sealed trait SchemaGen {
	def expandToTerminals(startSymbol: Nonterminal): List[Terminal]
}

class TerminalGenerator(
	val terminalGrammar: TerminalGrammarT,
	val nonterminalGrammar: NonterminalGrammarT) extends SchemaGen {

	def expandToTerminals(startSymbol: Nonterminal): List[Terminal] = {
		require(nonterminalGrammar contains startSymbol.symb, "Nonterminal grammar must contain start symbol")

		val rules = nonterminalGrammar(startSymbol.symb)
		val randRule = rules(Random.nextInt(rules.length))

		randRule.flatMap{ symb =>
			if (terminalGrammar contains symb) List(Terminal(terminalGrammar(symb)))
			else expandToTerminals((Nonterminal(symb)))
		}
	}
}