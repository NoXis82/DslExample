package schedule_dsl

import schedule_dsl.models.*
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DataContext {

    private val subjects = ArrayList<Subject>()

    private val students = ArrayList<Student>()

    private val teachers = ArrayList<Teacher>()

    private var startTime = LocalTime.of(8, 0)

    var lessonIntervalInMinutes = 60


    fun AvailabilityTable.day(dayOfWeek: DayOfWeek, from: String, to: String? = null): DayPointer {
        for (lessonIndex in time(from, to)) {
            this[dayOfWeek.ordinal, lessonIndex] = true
        }
        return DayPointer(this, dayOfWeek)
    }

    fun AvailabilityTable.friday(from: String, to: String? = null) = day(DayOfWeek.FRIDAY, from, to)

    fun AvailabilityTable.saturday(from: String, to: String? = null) = day(DayOfWeek.SATURDAY, from, to)

    fun AvailabilityTable.sunday(from: String, to: String? = null) = day(DayOfWeek.SUNDAY, from, to)

    fun AvailabilityTable.thursday(from: String, to: String? = null) = day(DayOfWeek.THURSDAY, from, to)

    fun AvailabilityTable.wednesday(from: String, to: String? = null) = day(DayOfWeek.WEDNESDAY, from, to)

    fun AvailabilityTable.monday(from: String, to: String? = null) = day(DayOfWeek.MONDAY, from, to)

    fun Teacher.availability(init: AvailabilityTable.() -> Unit) = this.availability.init()

    fun Teacher.subjectIndexes(vararg indexes: Int) {
        this.subjects.addAll(indexes.map { this@DataContext.subjects[it] })
    }

    fun teacher(init: Teacher.() -> Unit) = Teacher().apply {
        name = "Teacher " + teachers.size
        init()
        teachers.add(this)
    }


    fun student(init: Student.() -> Unit) = Student().apply {
        name = "Student " + students.size
        init()
        students.add(this)
    }

    fun Student.subjectIndexes(vararg indexes: Int) {
        this.subjectRequirements.addAll(indexes.map { subjects[it] })
    }

    fun subjects(vararg names: String) {
        names.mapTo(subjects) { Subject(it) }
    }

    fun startFrom(time: String) {
        startTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(time))
    }

    fun buildDataSet() = DataSet().apply {
        teachers.addAll(this@DataContext.teachers)
        students.addAll(this@DataContext.students)
    }

    fun time(from: String, to: String? = null): IntRange {
        val fromTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(from))
        val toTime = to?.let { LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(to)) } ?: fromTime.plusHours(1)
        val secondsFromStart = fromTime.toSecondOfDay() - startTime.toSecondOfDay()
        val startLessonIndex = secondsFromStart / (lessonIntervalInMinutes * 60)
        val secondsToEnd = toTime.toSecondOfDay() - startTime.toSecondOfDay()
        val endLessonIndex = secondsToEnd / (lessonIntervalInMinutes * 60) - 1
        return startLessonIndex..endLessonIndex
    }
}
