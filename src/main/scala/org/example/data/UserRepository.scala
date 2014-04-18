package org.example.data

import java.util.HashMap

import scala.collection.JavaConversions._

import org.springframework.stereotype.Component

import org.example.domain.User

/**
 * Data access operations on users.
 */
@Component
class UserRepository {
  /**
   * Adds a user.
   *
   * @param user The {@link User} to add.
   */
  def add(user: User) {
    if (user != null) {
      UserRepository.STORE.put(user.id, user)
    }
  }

  /**
   * Deletes a user with a specified identifier.
   *
   * @param id The unique identifier of the user to delete.
   */
  def delete(id: Long) {
    UserRepository.STORE.remove(id)
  }

  /**
   * Finds a user with a specified identifier.
   *
   * @param id The unique identifier of the user to find.
   */
  def find(id: Long) = UserRepository.STORE.get(id)

  /**
   * Finds all users.
   *
   * @return A {@link Collection} of {@link User}s.
   */
  def findAll = UserRepository.STORE.values
}

private object UserRepository {
  private val STORE = new HashMap[Long, User]
}
