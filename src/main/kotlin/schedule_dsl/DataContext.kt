package schedule_dsl

import schedule_dsl.models.Student
import schedule_dsl.models.Subject
import schedule_dsl.models.Teacher
import java.time.LocalTime

class DataContext {

    private val subjects = ArrayList<Subject>()

    private val students = ArrayList<Student>()

    private val teachers = ArrayList<Teacher>()

    private var startTime = LocalTime.of(8, 0)


    fun buildDataSet() = DataSet().apply {
        teachers.addAll(this@DataContext.teachers)
        students.addAll(this@DataContext.students)
    }
}