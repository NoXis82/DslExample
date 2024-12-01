import org.assertj.core.api.AssertionsForClassTypes.assertThat
import schedule_dsl.AssertionsContext
import schedule_dsl.SchedulingResults
import schedule_dsl.models.Event

class AssertionsContext(val scheduledEvents: Set<Event>)

infix fun SchedulingResults.assertions(init: AssertionsContext.() -> Unit) =
    AssertionsContext(this.scheduledEvents).init()

infix fun <T : Any?> T.shouldNotEqual(expected: T) {

//    Assert.assertThat(this, not(equalTo(expected)))
    assertThat(this).isNotEqualTo(expected)
}

infix fun <T : Any?> T.shouldEqual(expected: T) {
//    Assert.assertThat(this, equalTo(expected))
    assertThat(this).isEqualTo(expected)
}