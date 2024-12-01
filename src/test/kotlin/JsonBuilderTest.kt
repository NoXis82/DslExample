import json_builder.*
import kotlin.test.Test
import org.assertj.core.api.Assertions.*

class JsonBuilderTest {

    fun x() {
        val j = Json(
            JsonObject(
                mapOf(
                    "name" to JsonString("Any"),
                    "age" to JsonNumber(22)
                )
            )
        )

    }

    @Test
    fun simple() {
        val j = json {
            number(10)
        }

        assertThat(j.value).isInstanceOfSatisfying(JsonNumber::class.java) {
            assertThat(it.value).isEqualTo(10)
        }

        println(j)
    }

    @Test
    fun arrayTest() {
        val j = json {
            array {
                number(10)
                nil()
                string("hello")
            }
        }

        assertThat(j.value).isInstanceOfSatisfying(JsonArray::class.java) {
            assertThat(it.value).containsExactly(JsonNumber(10), null, JsonString("hello"))
        }

        println(j)
    }

    @Test
    fun hash() {
        val j = json {
            obj {
                "a" to 10
                "b" to array {
                    //"z" to 1 // <- to метод контекста JsonObjectBuilder можно использовать @DslMarker
                    number(10)
                    number(20)
                }
                "c" to obj {
                    "x" to 1
                }
            }
        }

        assertThat(j.value).isInstanceOfSatisfying(JsonObject::class.java) {
            assertThat(it.value)
                .containsEntry("a", JsonNumber(10))
                .containsEntry("b", JsonArray(listOf(JsonNumber(10), JsonNumber(20))))
                .containsEntry("c", JsonObject(mapOf("x" to JsonNumber(1))))
        }

        println(j)
    }
}