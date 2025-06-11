package at.seik.randomsvc

import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class PingConfiguration {

    @Bean
    fun kubernetesClient(): KubernetesClient {
        return KubernetesClientBuilder()
            .build()
    }


}
