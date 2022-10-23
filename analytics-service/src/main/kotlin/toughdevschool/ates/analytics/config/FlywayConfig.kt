package toughdevschool.ates.analytics.config

import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@Configuration
class FlywayConfig(private val props: FlywayProperties) {

    @Bean(initMethod = "migrate")
    fun flyway() = Flyway(
        Flyway.configure()
            .baselineOnMigrate(true)
            .locations(*props.locations.toTypedArray())
            .dataSource(props.url, props.user, props.password)
    )

    @Validated
    @Component
    @ConfigurationProperties(prefix = "spring.flyway")
    class FlywayProperties {

        @NotEmpty
        lateinit var locations: List<String>

        @NotEmpty
        var url: String? = null

        @NotEmpty
        var user: String? = null

        @NotEmpty
        var password: String? = null
    }
}
