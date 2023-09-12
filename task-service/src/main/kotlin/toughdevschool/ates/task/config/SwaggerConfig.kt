package toughdevschool.ates.task.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag

@OpenAPIDefinition(
    info = Info(
        title = "Tasks API"
    ),
    tags = [
        Tag(name = "Tasks"),
    ]
)
class SwaggerConfig
