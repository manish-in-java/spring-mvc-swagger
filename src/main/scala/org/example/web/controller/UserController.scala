package org.example.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{ PathVariable, RequestBody, RequestMapping, RequestMethod, RestController }

import com.wordnik.swagger.annotations.{ Api, ApiOperation }

import org.example.domain.User
import org.example.service.UserService

/**
 * User operations controller.
 */
@Api(description = "User API", value = "user")
@RestController
class UserController {
  @Autowired
  protected[this] var service: UserService = _

  /**
   * Adds a user.
   */
  @ApiOperation(value = "Adds a user")
  @RequestMapping(method = Array(RequestMethod.PUT), value = Array("/user"))
  def add(@RequestBody user: User) = this.service.add(user)

  /**
   * Deletes a user.
   */
  @ApiOperation(value = "Deletes a user")
  @RequestMapping(method = Array(RequestMethod.DELETE), value = Array("/user/{id}"))
  def delete(@PathVariable id: Long) = this.service.delete(id)

  /**
   * Finds a user.
   */
  @ApiOperation(value = "Finds a user with a specified identifier")
  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/user/{id}"))
  def find(@PathVariable id: Long) = this.service.find(id)

  /**
   * Finds all users.
   */
  @ApiOperation(value = "Finds all users")
  @RequestMapping(method = Array(RequestMethod.GET), value = Array("/user"))
  def findAll = this.service.findAll
}
