package org.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.example.data.UserRepository
import org.example.domain.User

/**
 * Business operations on users.
 */
@Service
class UserService {
  @Autowired
  protected[this] var repository: UserRepository = _

  /**
   * Adds a user.
   *
   * @param user The {@link User} to add.
   */
  def add(user: User) {
    this.repository.add(user)
  }

  /**
   * Deletes a user with a specified identifier.
   *
   * @param id The unique identifier of the user to delete.
   */
  def delete(id: Long) {
    this.repository.delete(id)
  }

  /**
   * Finds a user with a specified identifier.
   *
   * @param id The unique identifier of the user to find.
   */
  def find(id: Long) = this.repository.find(id)

  /**
   * Finds all users.
   *
   * @return A {@link Collection} of {@link User}s.
   */
  def findAll = this.repository.findAll
}
