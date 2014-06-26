
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
class MyFormatsSpec extends Specification {

  "The sqlDateFormat" should {
    "bind and unbind to sql.Dates" in {
      val tz = TimeZone.getTimeZone("GMT")
      val data = Map("date" -> "2014-06-26")
      val bound = {
        val cal = Calendar.getInstance(tz)
        cal.set(2014, 5, 26)
        val utilDate = cal.getTime
        val millis = utilDate.getTime
        new java.sql.Date(millis)
      }
      val format = MyFormats.sqlDateFormat("yyyy-MM-dd", tz)
      format.bind("date", data) should beRight like {
        case Right(date) =>
          val cal = Calendar.getInstance(tz)
          cal.setTime(date)
          cal.get(Calendar.YEAR) must_== 2014
          cal.get(Calendar.MONTH) must_== 5
          cal.get(Calendar.DAY_OF_MONTH) must_== 26
      }
      format.unbind("date", bound) should equalTo (data)
    }
  }

}
