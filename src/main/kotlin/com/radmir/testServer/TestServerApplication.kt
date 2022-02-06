package com.radmir.testServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestServerApplication

fun main(args: Array<String>) {
	runApplication<TestServerApplication>(*args)
}
