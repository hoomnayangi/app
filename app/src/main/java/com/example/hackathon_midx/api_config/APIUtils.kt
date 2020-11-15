package com.example.hackathon_midx.api_config

class APIUtils {
    private val baseURL = "https://18895f07c959.ngrok.io/"

    fun getServer(): APIService? {
        return APIClient().getClient(baseURL).create(APIService::class.java)
    }
}