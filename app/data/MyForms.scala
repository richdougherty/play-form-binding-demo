package data

import java.util.TimeZone
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format._
import play.api.mvc._

object MyForms {

  def sqlDate(pattern: String, timeZone: TimeZone = TimeZone.getDefault) =
    FieldMapping[java.sql.Date]()(MyFormats.sqlDateFormat(pattern, timeZone))

  def double =
    FieldMapping[Double]()(Formats.doubleFormat)

}