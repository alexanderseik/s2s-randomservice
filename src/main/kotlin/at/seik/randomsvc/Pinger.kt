package at.seik.randomsvc

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Url
import io.ktor.http.append
import io.ktor.http.headers
import io.ktor.network.sockets.SocketTimeoutException
import java.time.OffsetDateTime
import kotlinx.coroutines.runBlocking

class Pinger {
    companion object {
        fun ping(url: String) {
            runBlocking {
                val client = createHttpClientWithTimeout()

                try {
                    println("Calling: $url")
                    val response = client.get(url) {
                        headers {
                            append(HttpHeaders.Accept, ContentType.Text.Plain)
                        }
                    }
                    println("${OffsetDateTime.now()} - ${response.status} $url -> ${response.bodyAsText()}")
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    client.close()
                }
            }
        }

        fun createHttpClientWithTimeout(): HttpClient {
            return HttpClient(CIO) {
                install(HttpTimeout) {
                    connectTimeoutMillis = 2000
                    requestTimeoutMillis = 2000
                    socketTimeoutMillis = 2000
                }
            }
        }

    }
}
