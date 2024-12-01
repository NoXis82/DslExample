package schedule_dsl.models

import schedule_dsl.Matrix

//Таблица доступности
typealias AvailabilityTable = Matrix<Boolean>

//Расписание
typealias Schedule = Matrix<Event?>

val DAYS_PER_WEEK = 7

val LESSONS_PER_DAY = 10


open class Resource : Identifiable() {
    var name: String? = null
    val schedule = Schedule(DAYS_PER_WEEK, LESSONS_PER_DAY, init = { _, _ -> null })
}