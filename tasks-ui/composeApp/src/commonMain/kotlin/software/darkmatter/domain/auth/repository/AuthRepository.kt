package software.darkmatter.domain.auth.repository

import software.darkmatter.domain.auth.Auth

interface AuthRepository {

    fun getCurrentAuth(): Auth?

    fun setCurrentAuth(auth: Auth)

    fun clearCurrentAuth()
}
