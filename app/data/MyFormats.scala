package data

import java.util.TimeZone
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format._
import play.api.mvc._

object MyFormats {

  def sqlDateFormat(pattern: String, timeZone: TimeZone = TimeZone.getDefault): Formatter[java.sql.Date] = new Formatter[java.sql.Date] {
    val utilDateFormatter: Formatter[java.util.Date] = Formats.dateFormat(pattern, timeZone)

    override val format = utilDateFormatter.format

    def bind(key: String, data: Map[String, String]) = utilDateFormatter.bind(key, data).right.map(utilDate => new java.sql.Date(utilDate.getTime))

    def unbind(key: String, value: java.sql.Date) = utilDateFormatter.unbind(key, value)
  }

}