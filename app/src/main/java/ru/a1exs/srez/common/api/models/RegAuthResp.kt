package ru.a1exs.srez.common.api.models

data class RegAuthResp(
    val accessToken : String,
    val accessTokenExpiredAt : String,
    val refreshToken : String,
    val refreshTokenExpiredAt : String
)
