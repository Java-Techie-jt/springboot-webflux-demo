package com.myproject.webflux.handler

import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

import com.myproject.webflux.dto.Customer

import org.springframework.web.reactive.function.server.ServerRequest

import reactor.core.publisher.Flux

import com.myproject.webflux.dao.CustomerDao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Function


@Service
class CustomerHandler {
    @Autowired
    private val dao: CustomerDao? = null
    fun loadCustomers(request: ServerRequest?): Mono<ServerResponse> {
        val customerList: Flux<Customer> = dao!!.getCustomerList()
        return ServerResponse.ok().body(customerList, Customer::class.java)
    }

    fun findCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerId = Integer.valueOf(request.pathVariable("input"))
        // dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
        val customerMono: Mono<Customer> = dao!!.getCustomerList().filter { c->c.id == customerId }.next()
        return ServerResponse.ok().body(customerMono, Customer::class.java)
    }

    fun saveCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerMono = request.bodyToMono(Customer::class.java)
        val saveResponse = customerMono.map { dto: Customer -> dto.id.toString() + ":" + dto.name  }
        return ServerResponse.ok().body(saveResponse, String::class.java)
    }
}
