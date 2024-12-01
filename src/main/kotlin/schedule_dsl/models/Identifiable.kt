package schedule_dsl.models

import java.util.*

open class Identifiable(open var id: UUID = UUID.randomUUID()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Identifiable) return false
        return id == other.id
    }

    override fun hashCode() = id.hashCode()
}