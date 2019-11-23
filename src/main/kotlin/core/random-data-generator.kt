package core

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

/** Extension para ClosedRange<Double> que gera um número BigDecimal aleatório com determinado [scale]. */
fun ClosedRange<Double>.nextBigDecimal(scale: Int): BigDecimal {
    return Random.nextDouble(this.start, this.endInclusive).toBigDecimal().setScale(scale, RoundingMode.HALF_EVEN)
}
