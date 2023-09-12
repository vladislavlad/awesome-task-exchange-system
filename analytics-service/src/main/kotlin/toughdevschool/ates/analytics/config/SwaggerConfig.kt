package toughdevschool.ates.analytics.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag

@OpenAPIDefinition(
    info = Info(
        title = "Analytics API"
    ),
    tags = [
        Tag(name = "Analytics"),
    ]
)
class SwaggerConfig
