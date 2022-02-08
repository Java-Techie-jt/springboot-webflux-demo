package com.myproject.webflux


import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

class MonoFluxTest {

    @Test
    fun testMono(){
        val monoString: Mono<String> = Mono.just("My Mono String")
            .log()
        monoString.subscribe({x-> println("Error Not Occurred : $x")}){
                e->println("Error Occurred : "+e.message)
        }
    }

    @Test
    fun testMonoWithError() {
        val monoString: Mono<Any> = Mono.just("My Mono String")
            .then(Mono.error<Any>(RuntimeException("Exception occurred in Mono")))
            .log()
        monoString.subscribe({x-> println("Error Not Occurred : $x")}){
                e->println("Error Occurred : "+e.message)
        }
    }

    @Test
    fun testFlux(){
        val fluxString: Flux<String> = Flux.just("StringA","StringB","StringC")
            .concatWithValues("StringD","StringE")
            .log()
        fluxString.subscribe({x-> println("Error Not Occurred : $x")}){
                e->println("Error Occurred : "+e.message)
        }
    }

    @Test
    fun testFluxWithError(){
        val fluxString: Flux<String> = Flux.just("StringA","StringB","StringC")
            .concatWithValues("StringD","StringE")
            .concatWith(Flux.error(RuntimeException("Exception Occurred in Flux")))
            .concatWithValues("StringF")
            .log()
        fluxString.subscribe({x-> println("Error Not Occurred : $x")}){
                e->println("Error Occurred : "+e.message)
        }
    }

}
