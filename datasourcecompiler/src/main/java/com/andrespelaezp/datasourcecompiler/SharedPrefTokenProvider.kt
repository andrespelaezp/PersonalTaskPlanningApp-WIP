package com.andrespelaezp.datasourcecompiler

import com.andrespelaezp.datasourcecompiler.api.TokenProvider
import com.andrespelaezp.datasourcecompiler.keys.orgKey
import java.util.Base64

class TokenProviderImpl : TokenProvider {

    private val secretKey = orgKey
    private val expirationTimeMillis = 60 * 60 * 1000 // 1 hour

    override fun getToken(): String {
        return generateToken()
    }

    private fun generateToken(): String {
        val token = "apikey:$secretKey"
        val encodedCredentials = Base64.getEncoder().encodeToString(token.toByteArray())
        return encodedCredentials
    }
}