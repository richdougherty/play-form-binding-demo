package controllers

import data._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  case class UserData(number: Double, date: java.sql.Date)

  val userForm = Form(
    mapping(
      "number" -> MyForms.double,
      "date" -> MyForms.sqlDate("yyyy-mm-dd")
    )(UserData.apply)(UserData.unapply)
  )

  def handleForm = Action { implicit req =>
    userForm.bindFromRequest.fold(
      errors => BadRequest(errors.toString),
      userData => Ok(userData.toString)
    )
  }

}