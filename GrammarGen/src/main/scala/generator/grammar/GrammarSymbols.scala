package generator.grammar

sealed trait Symb { def symb: String }

case class Terminal(val symb: String) extends Symb

case class Nonterminal(val symb: String) extends Symb