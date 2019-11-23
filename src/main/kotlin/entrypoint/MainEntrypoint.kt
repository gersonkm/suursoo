package entrypoint

import core.Registro
import core.nextBigDecimal
import db.DatabaseConfig
import db.RegistroTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {

    /* abre uma conexão com o banco de dados */
    Database.connect(DatabaseConfig.dbJdbcUrl, DatabaseConfig.dbDriver, DatabaseConfig.dbUser, DatabaseConfig.dbPassword)

    /* abre uma transação */
    transaction {
        addLogger(StdOutSqlLogger)

        /* cria a tabela 'registro' (caso não exista) */
        SchemaUtils.create(RegistroTable)

        /* itera de 1 a 100 (isto é, 100 vezes) */
        (1..100)
            .map {

                // Gera registro com dados aleatórios

                Registro(
                    temperatura = (0.0..100.0).nextBigDecimal(2),
                    umidade = (0.0..100.0).nextBigDecimal(2)
                )
            }
            .forEach { registro ->

                // Grava registro no banco de dados

                RegistroTable.insert {
                    it[temperatura] = registro.temperatura
                    it[umidade] = registro.umidade
                    it[resultado] = registro.resultado
                }
            }

    }

}
