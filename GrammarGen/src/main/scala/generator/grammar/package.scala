package generator

package object grammar {
	type NonterminalGrammarT = Map[String, List[List[String]]]
	type TerminalGrammarT = Map[String, String]
	type TokensCatT = scala.collection.mutable.Map[String, List[String]]
}