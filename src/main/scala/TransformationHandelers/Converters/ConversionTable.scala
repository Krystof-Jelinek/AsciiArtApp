package TransformationHandelers.Converters

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class ConversionTable {
  private val charTable: ArrayBuffer[Char] = ArrayBuffer[Char]('$','@','B','%','8','&','W','M','#','*','o','a','h'
    ,'k','b','d','p','q','w','m','Z','O','0','Q','L','C','J','U','Y','X','z','c','v','u','n','x','r','j'
    ,'f','t','/','\\','|','(','1','{','}','[',']','?','-','_','+','~','<','>','i','!','l','I',';',':'
    ,'"','^','`','\'','.',' ')

  def setTable(input : String) : Unit = {
    if(input.isEmpty){
      throw IllegalArgumentException("Cant have empty conversion table")
    }
    charTable.clear()
    charTable ++= input.toCharArray
  }

  def length () : Int = {
    charTable.length
  }

  def setPredifinedTable(index : Int) : Unit = {
    if(index < 0 || index > 4){
      throw IndexOutOfBoundsException("There are only 5 predifined conversion tables numbered/named 0 to 4")
    }

    val filepath = "src/pictures/predifinedTables.txt"

    val src = Source.fromFile(filepath)

    val lines = src.getLines().toArray
    src.close()

    val line = lines(index)

    setTable(line)
  }

  def apply (index: Int): Char = {
    if (index >= 0 && index < charTable.length) {
      charTable(index)
    } else {
      throw new IndexOutOfBoundsException(s"Index $index is out of bounds.")
    }
  }

}
