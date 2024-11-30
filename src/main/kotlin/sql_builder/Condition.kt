package sql_builder

abstract class Condition {

    fun and(initializer: Condition.() -> Unit) {
        addCondition(And().apply(initializer))
    }

    fun or(initializer: Condition.() -> Unit) {
        addCondition(Or().apply(initializer))
    }

    infix fun String.eq(value: Any?) {
        addCondition(Eq(this, value))
    }

    protected abstract fun addCondition(condition: Condition)
}

//class And : Condition() {
//    private val conditions = mutableListOf<Condition>()
//
//    override fun addCondition(condition: Condition) {
//        conditions += condition
//    }
//
//
//    override fun toString(): String {
//        return if (conditions.size == 1) {
//            conditions.first().toString()
//        } else {
//            conditions.joinToString(prefix = "(", postfix = ")", separator = " and ")
//        }
//    }
//}

class Eq(private val column: String, private val value: Any?) : Condition() {

    init {
        if (value != null && value !is Number && value !is String) {
            throw IllegalArgumentException(
                "Only <null>, numbers and strings values can be used in the 'where' clause")
        }
    }

    override fun addCondition(condition: Condition) {
        throw IllegalStateException("Can't add a nested condition to the sql 'eq'")
    }

    override fun toString(): String {
        return when (value) {
            null -> "$column is null"
            is String -> "$column = '$value'"
            else -> "$column = $value"
        }
    }
}

