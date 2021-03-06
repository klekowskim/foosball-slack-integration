package pl.aetas.slack.foos.pushq

import com.google.gson.Gson
import pl.aetas.slack.foos.ApplicationProperties
import java.net.URL
import java.util.*

open class PushqSystem {

    val gson: Gson = Gson()

    val PUSHQ_URL_API_PLAYERS = ApplicationProperties.get("pushq.url.api.players")
    val PUSHQ_URL_API_LEMSTAT = ApplicationProperties.get("pushq.url.api.lemstat")


    open fun users(): List<String> {
        val response = URL(PUSHQ_URL_API_PLAYERS).readText()
        val stats: ArrayList<Map<String, String>> = gson.fromJson(response, typeLiteral<ArrayList<Map<String, String>>>())
        return stats.map { it.get("name")!! }
    }

    open fun ranking(): List<String> {
        val response = URL(PUSHQ_URL_API_LEMSTAT).readText()
        val stats: ArrayList<Map<String, String>> = gson.fromJson(response, typeLiteral<ArrayList<Map<String, String>>>())
        return stats.map { it.get("name")!! }
    }

    inline private fun <reified T: List<Map<String, String>>> typeLiteral() = T::class.java
}