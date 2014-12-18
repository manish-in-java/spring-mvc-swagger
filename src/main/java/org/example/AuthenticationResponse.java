package org.example;

/**
 * Represents a response to authenticate an application user.
 */
public class AuthenticationResponse
{
  private String token;

  /**
   * Sets a unique, secure token that can be used with subsequent requests.
   *
   * @param token A unique, secure token that can be used with subsequent requests.
   */
  public AuthenticationResponse(final String token)
  {
    this.token = token;
  }

  /**
   * Gets a unique, secure token that can be used with subsequent requests.
   *
   * @return A unique, secure token that can be used with subsequent requests.
   */
  public final String getToken()
  {
    return this.token;
  }
}
