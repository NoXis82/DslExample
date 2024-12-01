package schedule_dsl.models

class Teacher : Resource() {
    var subjects = HashSet<Subject>() //Предметы
    val availability = AvailabilityTable(DAYS_PER_WEEK, LESSONS_PER_DAY, { _, _ -> false })
}