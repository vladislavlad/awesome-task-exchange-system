package software.darkmatter.domain.auth

data class Auth(
    val accessToken: String,
    val refreshToken: String,
)
