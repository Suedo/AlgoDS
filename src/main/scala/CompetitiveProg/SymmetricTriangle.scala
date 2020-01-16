package CompetitiveProg

object SymmetricTriangle extends App {
  val height = 10

  val generateLine = (height: Int, i: Int) => {
    val spaces: String = " "*(height - (i + 1))
    val stars: String = "*"*((i*2) + 1)
    spaces + stars
  }

  (0 until height) foreach(i => println(generateLine(height, i)))
}

/*
---------------------
Output:
---------------------
         *
        ***
       *****
      *******
     *********
    ***********
   *************
  ***************
 *****************
*******************

 */