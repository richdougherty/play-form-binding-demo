import data._
import java.util.{ Calendar, TimeZone }
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.data._
import play.api.data.Forms._
import play.api.test._
import play.api.test.Helpers._
import scala.math.BigDecimal

@RunWith(classOf[JUnitRunner])
class MyFormsSpec extends Specification {

  "A simple mapping of Double" should {
    "return a Double" in {
      Form( "value" -> MyForms.double ).bind( Map( "value" -> "10.23") ).fold(
        formWithErrors => { "The mapping should not fail." must equalTo("Error") },
        { number => number must equalTo(10.23) }
      )
    }
  }

  "A simple mapping of java.sql.Date" should {
    "return a java.sql.Date" in {
      val tz = TimeZone.getTimeZone("GMT")
      Form( "value" -> MyForms.sqlDate("yyyy-MM-dd", tz) ).bind( Map( "value" -> "1999-12-31") ).fold(
        formWithErrors => { "The mapping should not fail." must equalTo("Error") },
        { date =>
            val cal = Calendar.getInstance(tz)
            cal.setTime(date)
            cal.get(Calendar.YEAR) must_== 1999
            cal.get(Calendar.MONTH) must_== 11
            cal.get(Calendar.DAY_OF_MONTH) must_== 31
        })
    }
  }

}
