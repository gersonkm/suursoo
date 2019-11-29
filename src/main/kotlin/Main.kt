import core.Registro
import core.nextBigDecimal
import db.DatabaseConfig
import db.RegistroTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

fun main() {

    /* abre uma conexão com o banco de dados */
    Database.connect("jdbc:sqlite:d:/temp/sqlite/db.db", "org.sqlite.JDBC")
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

    /* abre uma transação */
    transaction {

        /* pede para escrever o log da transação na saída padrão */
        addLogger(StdOutSqlLogger)

        /* cria a tabela 'registro' (caso não exista) */
        SchemaUtils.create(RegistroTable)

        /* itera de 1 a 100 (isto é, 100 vezes) */
        (1..100)
            .map {
                // gera registro com dados aleatórios
                Registro(
                    temperatura = (0.0..100.0).nextBigDecimal(scale = 2),
                    umidade = (0.0..100.0).nextBigDecimal(scale = 2)
                )
            }
            .forEach { registro ->
                // grava registro no banco de dados
                RegistroTable.insert {
                    it[temperatura] = registro.temperatura
                    it[umidade] = registro.umidade
                    it[resultado] = registro.resultado
                }
            }

    }

}
