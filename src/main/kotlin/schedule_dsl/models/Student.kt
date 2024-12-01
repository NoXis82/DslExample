package schedule_dsl.models

class Student : Resource() {
    val subjectRequirements = HashSet<Subject>() //запрашиваемые предметы
    override fun toString(): String {
        return "Student(name = $name)"
    }
}