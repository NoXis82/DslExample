package json_builder

sealed class JsonValueBuilder {

    internal var setter: (JsonValue?) -> Unit = {}

    fun number(value: Number) = JsonNumber(value).apply(setter)
    fun string(value: String) = JsonString(value).apply(setter)
    fun bool(value: Boolean) = JsonBoolean(value).apply(setter)
    fun nil() = setter(null).let { null }
    fun array(block: JsonArrayBuilder.() -> Unit) =
        JsonArrayBuilder().apply(block).build().also(setter)

    fun obj(block: JsonObjectBuilder.() -> Unit) =
        JsonObjectBuilder().apply(block).build().also(setter)

}

class JsonBuilder : JsonValueBuilder() {
    private var value: JsonValue? = null

    init {
        setter = { jsonValue -> value = jsonValue }
    }

    fun build(): Json = Json(value)
}


@JsonContext
class JsonArrayBuilder : JsonValueBuilder() {
    private val values = mutableListOf<JsonValue?>()

    init {
        setter = { jsonValue -> values.add(jsonValue) }
    }

    fun build(): JsonArray = JsonArray(values)
}

@JsonContext
class JsonObjectBuilder : JsonValueBuilder() {
    private val valueMap = mutableMapOf<String, JsonValue?>()

    infix fun String.to(value: Number) {
        valueMap[this] = JsonNumber(value)
    }

    infix fun String.to(value: String) {
        valueMap[this] = JsonString(value)
    }

    infix fun String.to(value: JsonValue?) {
        valueMap[this] = value
    }

    fun build(): JsonObject = JsonObject(valueMap)
}