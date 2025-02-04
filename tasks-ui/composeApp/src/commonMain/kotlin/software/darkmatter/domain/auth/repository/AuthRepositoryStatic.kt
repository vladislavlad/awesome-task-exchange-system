package software.darkmatter.domain.auth.repository

import software.darkmatter.domain.auth.Auth

class AuthRepositoryStatic : AuthRepository {

    private var _auth: Auth? = null

    override fun getCurrentAuth(): Auth? = _auth

    override fun setCurrentAuth(auth: Auth) {
        _auth = auth
    }

    override fun clearCurrentAuth() {
        _auth = null
    }
}
