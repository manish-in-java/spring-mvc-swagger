package org.example.web.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * Codec for converting between Scala objects and JSON.
 */
class ScalaObjectMapper extends ObjectMapper {
  this.registerModule(DefaultScalaModule)

  this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
}
