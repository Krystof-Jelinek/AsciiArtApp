package TransformationHandelers.Converters

import scala.collection.mutable.ArrayBuffer

class ConversionTable {
  private val charTable: ArrayBuffer[Char] = ArrayBuffer[Char]('$','@','B','%','8','&','W','M','#','*','o','a','h'
    ,'k','b','d','p','q','w','m','Z','O','0','Q','L','C','J','U','Y','X','z','c','v','u','n','x','r','j'
    ,'f','t','/','\\','|','(','1','{','}','[',']','?','-','_','+','~','<','>','i','!','l','I',';',':'
    ,'"','^','`','\'','.',' ')

  def setTable(input : String) : Unit = {
    charTable.clear()
    charTable ++= input.toCharArray
  }

  def length () : Int = {
    charTable.length
  }

  def apply (index: Int): Char = {
    if (index >= 0 && index < charTable.length) {
      charTable(index)
    } else {
      throw new IndexOutOfBoundsException(s"Index $index is out of bounds.")
    }
  }

}
