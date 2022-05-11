package _2020.CompetitiveProg

/*

Print This Rangoli Pattern.
Taken from Hacker Rank Python's String Manipulation section (courtesy soutam)

--------e--------
------e-d-e------
----e-d-c-d-e----
--e-d-c-b-c-d-e--
e-d-c-b-a-b-c-d-e
--e-d-c-b-c-d-e--
----e-d-c-d-e----
------e-d-e------
--------e--------

*/


object RangoliPrint extends App {

  private val alphabet: String = "abcdefghijklmnopqrstuvwxyz"

  def generateStartString(len: Int): String = {
    alphabet.slice(0, len).split("").mkString("-")
  }

  def flipAndMerge(str: String): String = {
    str.concat(str.substring(0,str.length - 1).reverse)
  }

  // only gives subsequences starting from the 1st char of original string
  def getSubsequences(str: String): Seq[String] = {

    val list: Seq[String] = for {
      i <- 0 to (str.length - 1)/2
    } yield str.substring(0,i*2 + 1)

    list
  }

  def combineDashesAndLetters(letter: String, totalLength: Int): String = {
    val dashes = "-"*(totalLength - letter.length)
    dashes.concat(letter)
  }

//  val maxStr = "e-d-c-b-a"
//  val maxStr = generateStartString(5) // "a-b-c-d-e"
  val maxStr = generateStartString(5).reverse // ""e-d-c-b-a"
  val maxLen = maxStr.length
  val subSeqs = getSubsequences(maxStr)

//  for(s <- subSeqs) println(s)

  val fullLines: Seq[String] = for (str <- subSeqs) yield flipAndMerge(combineDashesAndLetters(str, maxLen))
  private val revLines: Seq[String] = fullLines.slice(0, (fullLines.length - 1)).reverse

  val totalLines = fullLines ++ revLines

  for (l <- totalLines) println(l)

}