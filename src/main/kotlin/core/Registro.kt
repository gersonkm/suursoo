package core

import java.math.BigDecimal

data class Registro(
    val temperatura: BigDecimal,
    val umidade: BigDecimal
) {
    val resultado: Boolean = umidade >= 80.0.toBigDecimal()
}
