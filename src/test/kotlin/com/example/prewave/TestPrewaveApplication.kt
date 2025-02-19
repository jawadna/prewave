package com.example.prewave

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<PrewaveApplication>().with(TestcontainersConfiguration::class).run(*args)
}
