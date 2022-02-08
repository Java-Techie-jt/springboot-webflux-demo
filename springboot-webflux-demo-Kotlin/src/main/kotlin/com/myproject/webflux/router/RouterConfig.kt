package com.myproject.webflux.router

import com.myproject.webflux.handler.CustomerHandler
import com.myproject.webflux.handler.CustomerStreamHandler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*


@Configuration
class RouterConfig {

    @Autowired
    private val handler: CustomerHandler? = null

    @Autowired
    private val streamHandler: CustomerStreamHandler? = null
    @Bean
    fun routerFunction(): RouterFunction<ServerResponse?>? {
        return RouterFunctions.route()
            .GET("/router/customers") { request: ServerRequest? -> handler!!.loadCustomers(request)}
            .GET("/router/customers/stream"){ request:ServerRequest?->streamHandler!!.getCustomers(request) }
            .GET("/router/customer/{input}"){ request:ServerRequest?->handler!!.findCustomer(request!!) }
            .POST("/router/customer/save") { request:ServerRequest?->handler!!.saveCustomer(request!!) }
            .build()
    }
}