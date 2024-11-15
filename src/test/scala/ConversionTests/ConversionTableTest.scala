package ConversionTests

import ImageTransformation.Converters.ConversionTable
import org.scalatest.funsuite.AnyFunSuite

class ConversionTableTest extends AnyFunSuite{

  test("default table test"){
    val conversionTable = new ConversionTable
    assert(conversionTable(0) == '$')
    assert(conversionTable(67) == ' ')
    assert(conversionTable.length() == 68)
  }

  test("changing to custom table"){
    val conversionTable = new ConversionTable
    conversionTable.setTable("ABCDE")
    assert(conversionTable(0) == 'A')
    assert(conversionTable(4) == 'E')
    assert(conversionTable.length() == 5)
  }

  test("testing index out of bounds"){
    val conversionTable = new ConversionTable
    assertThrows[IndexOutOfBoundsException]{
      conversionTable(100)
    }
    assertThrows[IndexOutOfBoundsException] {
      conversionTable(-1)
    }
  }

  test("testing setting invalid alphabet") {
    val conversionTable = new ConversionTable

    assertThrows[IllegalArgumentException] {
      conversionTable.setTable("")
    }
  }

  test("first predifined table test") {
    val conversionTable = new ConversionTable
    conversionTable.setPredifinedTable(0)
    assert(conversionTable(0) == '@')
    assert(conversionTable(1) == '%')
    assert(conversionTable(2) == '#')
    assert(conversionTable(3) == '*')
    assert(conversionTable(4) == '+')
    assert(conversionTable(5) == '=')
    assert(conversionTable(6) == '-')
    assert(conversionTable(7) == ':')
    assert(conversionTable(8) == '.')
    assertThrows[IndexOutOfBoundsException] {
      conversionTable(9)
    }
  }

  test("last predifined table test") {
    val conversionTable = new ConversionTable
    conversionTable.setPredifinedTable(4)

    assert(conversionTable(0) == 'a')
    assert(conversionTable(1) == 'b')
    assert(conversionTable(2) == 'c')
    assert(conversionTable(3) == 'd')
    assert(conversionTable(4) == 'e')
    assert(conversionTable(5) == 'f')
    assert(conversionTable(6) == 'g')
    assert(conversionTable(7) == 'h')
    assertThrows[IndexOutOfBoundsException] {
      conversionTable(8)
    }
  }

  test("wrong predifined table test") {
    val conversionTable = new ConversionTable
    assertThrows[IndexOutOfBoundsException] {
      conversionTable.setPredifinedTable(-1)
    }
    assertThrows[IndexOutOfBoundsException] {
      conversionTable.setPredifinedTable(666)
    }
  }

}
