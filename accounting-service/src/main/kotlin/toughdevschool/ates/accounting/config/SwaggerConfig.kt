package toughdevschool.ates.accounting.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag

@OpenAPIDefinition(
    info = Info(
        title = "Accounting API"
    ),
    tags = [
        Tag(name = "Accounting"),
        Tag(name = "Transfer"),
        Tag(name = "History"),
    ]
)
class SwaggerConfig
