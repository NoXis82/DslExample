package schedule_dsl

object schedule {

    operator fun invoke(init: SchedulingContext.() -> Unit) = SchedulingContext().init()

}

class SchedulingContext {

    fun data(init: DataContext.() -> Unit): SchedulingResults {
    //    val context = DataContext().apply(init)
    //    val dataSet = context.buildDataSet()
        val scheduler = Scheduler()
        return scheduler.schedule(dataSet)
    }
}