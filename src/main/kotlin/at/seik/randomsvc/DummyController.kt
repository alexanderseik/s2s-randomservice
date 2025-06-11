package at.seik.randomsvc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyController {
    @GetMapping("/ping")
    @CrossOrigin(origins = ["*"])
    fun receive() : ResponseEntity<String> {
        return ResponseEntity.ok("pong")
    }
}
