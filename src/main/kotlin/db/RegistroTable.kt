package db

import org.jetbrains.exposed.sql.Table

/** Entidade que representa a tabela registro no banco de dados */
object RegistroTable : Table("registro") {
    val id = integer("id").autoIncrement().primaryKey()
    val temperatura = decimal("temperatura", precision = 5, scale = 2)
    val umidade = decimal("umidade", precision = 5, scale = 2)
    val resultado = bool("resultado")
}