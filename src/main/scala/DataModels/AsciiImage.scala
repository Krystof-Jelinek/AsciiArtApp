package DataModels

class AsciiImage(width: Int, height: Int) extends Image[Char](width, height, ' '){
  def getString : String = {
    var res = ""
    var x = 0
    var y = 0

    while (y < height) {
      while (x < width) {
        val char = getVal(x, y).get
        res += char
        x += 1
      }
      res += '\n'
      y += 1
      x = 0
    }

    return res
  }
}
