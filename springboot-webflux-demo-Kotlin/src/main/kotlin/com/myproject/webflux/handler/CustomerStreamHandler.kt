package com.myproject.webflux.handler

import com.myproject.webflux.dto.Customer

import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Flux

import org.springframework.web.reactive.function.server.ServerRequest

import reactor.core.publisher.Mono

import com.myproject.webflux.dao.CustomerDao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service


@Service
class CustomerStreamHandler {
    @Autowired
    private val dao: CustomerDao? = null
    fun getCustomers(request: ServerRequest?): Mono<ServerResponse> {
        val customersStream = dao!!.getCustomersStream()
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
            .body(customersStream, Customer::class.java)
    }
}