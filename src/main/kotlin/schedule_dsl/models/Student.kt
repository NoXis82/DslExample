package schedule_dsl.models

class Student : Resource() {
    val subjectRequirements = HashSet<Subject>() //запрашиваемые предметы
}