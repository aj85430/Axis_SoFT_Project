package com.manipal.demo.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
    }

    companion object {
        val DEFAULT_CONTACT: Contact = Contact(
                "Aman Jaiswal", "https://www.linkedin.com/in/aman-jaiswal-0ba354159/", "anshujai4@gmail.com")
        val DEFAULT_API_INFO = ApiInfo(
                "Library API", "Performed Library CRUD Operations on MySql", "1.0",
                "urn:tos", DEFAULT_CONTACT,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList())
        private val DEFAULT_PRODUCES_AND_CONSUMES: Set<String> = HashSet<String>(Arrays.asList("application/json"))
    }
}

