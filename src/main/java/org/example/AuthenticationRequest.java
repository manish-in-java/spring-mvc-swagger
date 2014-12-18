package org.example;

/**
 * Represents a request to authenticate an application user.
 */
public class AuthenticationRequest
{
  private String password;
  private String username;

  /**
   * Gets the password with which to authenticate the user.
   *
   * @return The password with which to authenticate the user.
   */
  public final String getPassword()
  {
    return this.password;
  }

  /**
   * Gets the username to use for authenticating the user.
   *
   * @return The username to use for authenticating the user.
   */
  public final String getUsername()
  {
    return this.username;
  }

  /**
   * Sets the password with which to authenticate the user.
   *
   * @param password The password with which to authenticate the user.
   */
  public final void setPassword(final String password)
  {
    this.password = password;
  }

  /**
   * Sets the username to use for authenticating the user.
   *
   * @param username The username to use for authenticating the user.
   */
  public final void setUsername(final String username)
  {
    this.username = username;
  }
}
