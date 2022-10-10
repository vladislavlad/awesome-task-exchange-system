package toughdevschool.ates.task.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import software.darkmatter.platform.security.jwt.service.JwtServerAuthenticationConverter

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val authenticationConverter: JwtServerAuthenticationConverter,
    private val authenticationManager: ReactiveAuthenticationManager,
) {

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(authenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(authenticationConverter)

        return http {
            addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            authorizeExchange {
                authorize("/authentication/**", permitAll)
                authorize(anyExchange, authenticated)
            }
            httpBasic { disable() }
            formLogin { disable() }
            csrf { disable() }
            cors { disable() }
        }
    }
}
