package com.myproject.webflux.controller

import com.myproject.webflux.dto.Customer
import com.myproject.webflux.service.CustomerService
import reactor.core.publisher.Flux

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/customers")
class CustomerController {
    @Autowired
    private val service: CustomerService? = null

    @GetMapping
    fun getAllCustomers():List<Customer>{
        return service!!.loadAllCustomers()
    }

    @GetMapping(value = ["/stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllCustomersStream(): Flux<Customer> {
        return service!!.loadAllCustomersStream()
    }
}