package json_builder

data class Json(
    val value: JsonValue?
)

sealed interface JsonValue

data class JsonNumber(val value: Number): JsonValue
data class JsonString(val value: String): JsonValue
data class JsonBoolean(val value: Boolean): JsonValue
data class JsonArray(val value: List<JsonValue?>): JsonValue
data class JsonObject(val value: Map<String, JsonValue?>): JsonValue


fun json(block: JsonValueBuilder.() -> Unit): Json {
    val builder = JsonBuilder()
    builder.block()
    return builder.build()
}

