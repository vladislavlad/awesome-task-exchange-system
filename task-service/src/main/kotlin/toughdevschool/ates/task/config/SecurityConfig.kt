package toughdevschool.ates.task.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.AuthorizeExchangeDsl
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import software.darkmatter.platform.security.jwt.service.JwtServerAuthenticationConverter
import toughdevschool.ates.task.domain.userRole.business.RoleNames

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val authenticationConverter: JwtServerAuthenticationConverter,
    private val authenticationManager: ReactiveAuthenticationManager,
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(authenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(authenticationConverter)

        return http {
            addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            authorizeExchange {
                swagger()
                authorize("/tasks/*/complete", hasAuthority(RoleNames.WORKER))
                authorize("/tasks/reassign", hasAnyAuthority(RoleNames.MANAGER, RoleNames.ADMIN))
                authorize("/actuator/**", permitAll)
                authorize(anyExchange, authenticated)
            }
            httpBasic { disable() }
            formLogin { disable() }
            csrf { disable() }
            cors { }
        }
    }

    private fun AuthorizeExchangeDsl.swagger() {
        authorize("/v3/api-docs", permitAll)
        authorize("/v3/api-docs/swagger-config", permitAll)
        authorize("/webjars/swagger-ui/**", permitAll)
        authorize("/swagger-ui.html", permitAll)
    }
}
