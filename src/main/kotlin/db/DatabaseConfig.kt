package db

object DatabaseConfig {
    const val dbHost = "34.70.47.249"
    const val dbPort = 3306
    const val dbName = "teste"

    const val dbJdbcUrl = "jdbc:mysql://$dbHost:$dbPort/$dbName?reconnect=true"

    /* credenciais */
    const val dbUser = "suursoo"
    const val dbPassword = "suursoo777"

    const val dbDriver = "com.mysql.cj.jdbc.Driver"

}