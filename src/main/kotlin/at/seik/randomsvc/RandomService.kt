package at.seik.randomsvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RandomService

fun main(args: Array<String>) {
    runApplication<RandomService>(*args)
}
