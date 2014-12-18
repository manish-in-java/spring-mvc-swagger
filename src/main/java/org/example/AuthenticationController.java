package org.example;

import com.wordnik.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * User authentication controller.
 */
@Api(value = "/authenticate", description = "Authenticates an external actor so that the actor can start interacting with the application.")
@RequestMapping("/authenticate")
@RestController
public class AuthenticationController
{
  /**
   * Authenticates a user.
   *
   * @param request An {@link AuthenticationRequest} containing authentication information.
   * @return An {@link AuthenticationResponse} containing a secure token that be used
   * with subsequent requests.
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public AuthenticationResponse authenticate(@RequestBody final AuthenticationRequest request)
  {
    return new AuthenticationResponse(UUID.randomUUID().toString());
  }
}
