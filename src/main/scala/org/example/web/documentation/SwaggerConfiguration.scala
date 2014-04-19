package org.example.web.documentation

import java.util.Arrays

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{ Bean, Configuration }
import org.springframework.http.{ HttpEntity, ResponseEntity }

import com.fasterxml.classmate.TypeResolver

import com.mangofactory.swagger.configuration.{ SpringSwaggerConfig, SwaggerGlobalSettings }
import com.mangofactory.swagger.core.SwaggerApiResourceListing
import com.mangofactory.swagger.models.alternates.{ Alternates, AlternateTypeProvider, WildcardType }
import com.mangofactory.swagger.scanners.ApiListingReferenceScanner

import com.wordnik.swagger.model.ApiInfo

/**
 * Swagger REST API Documentation configuration.
 */
@Configuration
class SwaggerConfiguration {
  @Autowired
  protected[this] var config: SpringSwaggerConfig = _

  /**
   * The ApiListingReferenceScanner does most of the work.
   * Scans the appropriate spring RequestMappingHandlerMappings
   * Applies the correct absolute paths to the generated swagger resources
   */
  @Bean
  def apiListingReferenceScanner: ApiListingReferenceScanner = {
    val apiListingReferenceScanner = new ApiListingReferenceScanner

    //Picks up all of the registered spring RequestMappingHandlerMappings for scanning
    apiListingReferenceScanner.setRequestMappingHandlerMapping(this.config.swaggerRequestMappingHandlerMappings)

    //Excludes any controllers with the supplied annotations
    apiListingReferenceScanner.setExcludeAnnotations(this.config.defaultExcludeAnnotations)

    //
    apiListingReferenceScanner.setResourceGroupingStrategy(this.config.defaultResourceGroupingStrategy);

    //Path provider used to generate the appropriate uri's
    apiListingReferenceScanner.setSwaggerPathProvider(this.config.defaultSwaggerPathProvider);

    //Must match the swagger group set on the SwaggerApiResourceListing
    apiListingReferenceScanner.setSwaggerGroup(SwaggerConfiguration.GROUP)

    //Only include paths that match the supplied regular expressions
    apiListingReferenceScanner.setIncludePatterns(SwaggerConfiguration.PATTERNS)

    return apiListingReferenceScanner
  }

  /**
   * Configures a Swagger scanner.
   */
  @Bean
  def swaggerApiResourceListing: SwaggerApiResourceListing = {
    //The group name is important and should match the group set on ApiListingReferenceScanner
    //Note that swaggerCache() is by DefaultSwaggerController to serve the swagger json
    val swaggerApiResourceListing = new SwaggerApiResourceListing(this.config.swaggerCache, SwaggerConfiguration.GROUP)

    //Set the required swagger settings
    swaggerApiResourceListing.setSwaggerGlobalSettings(this.swaggerGlobalSettings)

    //Use a custom path provider or springSwaggerConfig.defaultSwaggerPathProvider()
    swaggerApiResourceListing.setSwaggerPathProvider(this.config.defaultSwaggerPathProvider)

    //Supply the API Info as it should appear on swagger-ui web page
    swaggerApiResourceListing.setApiInfo(this.apiInfo)

    //Every SwaggerApiResourceListing needs an ApiListingReferenceScanner to scan the spring request mappings
    swaggerApiResourceListing.setApiListingReferenceScanner(this.apiListingReferenceScanner)

    return swaggerApiResourceListing
  }

  /**
   * Global swagger settings.
   */
  @Bean
  def swaggerGlobalSettings: SwaggerGlobalSettings = {
    val swaggerGlobalSettings = new SwaggerGlobalSettings
    swaggerGlobalSettings.setGlobalResponseMessages(this.config.defaultResponseMessages)
    swaggerGlobalSettings.setIgnorableParameterTypes(this.config.defaultIgnorableParameterTypes)

    val alternateTypeProvider = this.config.defaultAlternateTypeProvider;
    val typeResolver = new TypeResolver
    alternateTypeProvider.addRule(Alternates.newRule(typeResolver.resolve(classOf[ResponseEntity[Void]]),
      typeResolver.resolve(classOf[Void])))
    alternateTypeProvider.addRule(Alternates.newRule(typeResolver.resolve(classOf[ResponseEntity[WildcardType]], classOf[WildcardType]),
      typeResolver.resolve(classOf[WildcardType])))
    alternateTypeProvider.addRule(Alternates.newRule(typeResolver.resolve(classOf[HttpEntity[WildcardType]], classOf[WildcardType]),
      typeResolver.resolve(classOf[WildcardType])))
    swaggerGlobalSettings.setAlternateTypeProvider(alternateTypeProvider)

    return swaggerGlobalSettings
  }

  /**
   * Basic API information as returned to the client.
   */
  private def apiInfo = {
    new ApiInfo("IDEA Frame Inc. REST API",
      "REST API for the IDEA Frame Business Automation framework.",
      "http://www.ideaframe.com/terms",
      "support@ideaframe.com",
      "Proprietary",
      "http://www.ideaframe.com/terms")
  }
}

private object SwaggerConfiguration {
  private val GROUP = "ideaframe"
  private val PATTERNS = Arrays.asList("/user.*")
}
