package schedule_dsl

import schedule_dsl.models.Student
import schedule_dsl.models.Teacher

class DataSet {
    val students = HashSet<Student>() //Студенты
    val teachers = HashSet<Teacher>() //Учителя
}