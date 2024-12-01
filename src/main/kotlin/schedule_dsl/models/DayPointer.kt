package schedule_dsl.models

import java.time.DayOfWeek

data class DayPointer(
    val availabilityTable: AvailabilityTable,
    val dayOfWeek: DayOfWeek
) {
    operator fun plus(lessonIndexRange: IntRange): DayPointer {
        val (table, dayOfWeek) = this
        for (lessonIndex in lessonIndexRange) {
            table[dayOfWeek.ordinal, lessonIndex] = true
        }
        return this
    }
}
