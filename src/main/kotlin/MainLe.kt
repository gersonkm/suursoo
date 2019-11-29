import core.Registro
import core.nextBigDecimal
import db.DatabaseConfig
import db.RegistroTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.sql.Connection

fun main() {

    /* abre uma conexão com o banco de dados */
    Database.connect("jdbc:sqlite:d:/temp/sqlite/db.db", "org.sqlite.JDBC")
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

    /* abre uma transação */
    transaction {

        /* pede para escrever o log da transação na saída padrão */
        addLogger(StdOutSqlLogger)

        val file = File("D:\\temp\\sqlite\\a.txt ")

        val result = RegistroTable.selectAll().joinToString(separator = "\n") {
            "${it[RegistroTable.temperatura]},${it[RegistroTable.umidade]},${it[RegistroTable.resultado]}"
        }

        file.writeText(result)

    }

}
