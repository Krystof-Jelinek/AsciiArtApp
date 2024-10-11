import TransformationHandelers.Converters.ConversionTable
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

}
