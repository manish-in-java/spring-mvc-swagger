package org.example;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the web application.
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?>[] getRootConfigClasses()
  {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[] { WebConfiguration.class };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String[] getServletMappings()
  {
    return new String[] { "/" };
  }
}

/**
 * Provides configuration for the application.
 */
@ComponentScan("org.example")
@Configuration
@EnableWebMvc
@EnableSwagger class WebConfiguration
{
  @Autowired
  SpringSwaggerConfig swaggerConfig;

  /**
   * Overrides default Swagger configuration.
   */
  @Bean
  public SwaggerSpringMvcPlugin swaggerPlugin()
  {
    final ApiInfo apiInfo = new ApiInfo("Example API"
        , "Example API"
        , ""
        , ""
        , ""
        , "");

    return new SwaggerSpringMvcPlugin(this.swaggerConfig).swaggerGroup("api").apiInfo(apiInfo);
  }
}
