package at.seik.randomsvc

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.fabric8.kubernetes.api.model.Service
import io.fabric8.kubernetes.api.model.ServicePort
import io.fabric8.kubernetes.client.KubernetesClient
import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking
import org.apache.hc.client5.http.classic.HttpClient
import org.apache.hc.client5.http.config.ConnectionConfig
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.core5.http.io.SocketConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class PingScheduler(val kubernetesClient: KubernetesClient, @Value("\${url-schema}") val urlSchema: String) {

    data class ServiceConfig(val namespace: String, val name: String)

    @Scheduled(fixedRate = 1_000)
    fun run() {
        val content = System.getenv("SERVICES")

        val list = jacksonObjectMapper().readValue<List<ServiceConfig>>(content)

        val serviceList = list
            .flatMap { getPort(it) }

        val randomUrl = serviceList.random()

        val pingUrl = "$randomUrl/ping"

        Pinger.ping(pingUrl)
    }

    private fun getPort(service: ServiceConfig): List<String> {
        return listOf(toUrl(service, 8080))
    }

    private fun toUrl(service: ServiceConfig, port: Int) : String {
        return urlSchema.replace("%service", service.name)
            .replace("%namespace", service.namespace)
            .replace("%port", port.toString())

    }
}
