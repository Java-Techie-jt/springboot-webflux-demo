package com.myproject.webflux.dao

import com.myproject.webflux.dto.Customer
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.io.InputStream
import java.time.Duration
import java.util.function.Function
import java.util.function.IntConsumer
import java.util.function.IntFunction

import java.util.stream.Collectors

import java.util.stream.IntStream


@Component
class CustomerDao() {
    companion object {
        private fun sleepExecution(i: Int) {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
    fun getCustomers(): List<Any> {
        return IntStream.rangeClosed(1, 10)
            .peek { i: Int -> sleepExecution(i) }
            .peek { i: Int -> println("processing count : $i") }
            .mapToObj<Any> { i: Int -> Customer(i, "customer$i") }
            .collect(Collectors.toList())
    }
    fun getCustomersStream(): Flux<Customer>{
            return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext { i: Int -> println("processing count in stream flow : $i") }
                .map(Function { i: Int -> Customer(i, "customer$i") })
        }
    fun getCustomerList(): Flux<Customer>{
            return Flux.range(1, 50)
                .doOnNext { i: Int -> println("processing count in stream flow : $i") }
                .map(Function { i: Int -> Customer(i, "customer$i") })
        }
}