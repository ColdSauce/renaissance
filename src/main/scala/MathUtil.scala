/**
  * Created by campi on 12/10/2016.
  */
object MathUtil {
  implicit class FloatImprovement(i: Float) {
    def isBetween(one: Double, two: Double) = one < i && i < two
  }
}
