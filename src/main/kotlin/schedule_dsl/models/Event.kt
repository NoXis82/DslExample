package schedule_dsl.models

data class Event(
    val day: Int,
    val lesson: Int,
    val student: Student,
    val teacher: Teacher
)
