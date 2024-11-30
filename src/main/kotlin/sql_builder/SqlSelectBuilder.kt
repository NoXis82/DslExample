package sql_builder

class SqlSelectBuilder {

    private val columns = mutableListOf<String>()
    private lateinit var table: String
    private var condition: Condition? = null




    fun where(initializer: Condition.() -> Unit) {
        condition = And().apply(initializer)
    }

    fun from(table: String) {
        this.table = table
    }

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("At least one column should be defined")
        }
        if (this.columns.isNotEmpty()) {
            throw IllegalStateException("Detected an attempt to re-define columns to fetch. "
                    + "Current columns list: "
                    + "${this.columns}, new columns list: $columns")
        }
        this.columns.addAll(columns)
    }


    fun build(): String {
        if (!::table.isInitialized) {
            throw IllegalStateException("Failed to build an sql select - target table is undefined")
        }
        return toString()
    }

    override fun toString(): String {
        val columnsToFetch =
            if (columns.isEmpty()) {
                "*"
            } else {
                columns.joinToString(", ")
            }
        val conditionString =
            if (condition == null) {
                ""
            } else {
                " where $condition"
            }
        return "select $columnsToFetch from $table$conditionString"
    }
}

fun query(initializer: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(initializer)
}