package com.myproject.webflux.service

import com.myproject.webflux.dto.Customer

import reactor.core.publisher.Flux

import com.myproject.webflux.dao.CustomerDao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class CustomerService {
    @Autowired
    private val dao: CustomerDao? = null
    fun loadAllCustomers(): List<Customer> {   //non reactive
        val start = System.currentTimeMillis()
        val customers: List<Customer> = dao!!.getCustomers() as List<Customer>
        val end = System.currentTimeMillis()
        println("Total execution time : " + (end - start))
        return customers
    }

    fun loadAllCustomersStream(): Flux<Customer> {    // reactive
        val start = System.currentTimeMillis()
        val customers: Flux<Customer> = dao!!.getCustomersStream() as Flux<Customer>
        val end = System.currentTimeMillis()
        println("Total execution time : " + (end - start))
        return customers
    }
}