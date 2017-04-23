package com.github.pjozsef.control.udp

import com.github.pjozsef.control.common.SharedDataKeys
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.datagram.DatagramSocketOptions
import io.vertx.core.logging.LoggerFactory

class UdpVerticle : AbstractVerticle() {
    val log = LoggerFactory.getLogger(this::class.java)

    override fun start(startFuture: Future<Void>) {
        val token = config().getString("token")

        val opts = DatagramSocketOptions().setBroadcast(true)
        val socket = vertx.createDatagramSocket(opts)
        val port = config().getInteger("port")
        socket.listen(port, "0.0.0.0", { asyncResult ->
            if (asyncResult.succeeded()) {
                startFuture.complete()
                log.info("Udp server listening on $port")
                socket.handler({ packet ->
                    val senderPort = packet.sender().port()
                    val senderHost = packet.sender().host()
                    val message = packet.data().toString()
                    if (message == token) {
                        log.info("Valid request from $senderHost")
                        socket.send(createReply(), senderPort, senderHost, {})
                    } else {
                        log.info("Invalid request: $message, from $senderHost")
                    }
                })
            } else {
                startFuture.fail(asyncResult.cause())
                println("Listen failed ${asyncResult.cause()}")
            }
        })
    }

    private fun createReply(): String {
        val sd = vertx.sharedData()
        val webInfo = sd.getLocalMap<String, String>(SharedDataKeys.webInfo)

        val name = webInfo.get(SharedDataKeys.webInfoName)
        val os = webInfo.get(SharedDataKeys.webInfoOS)
        val port = webInfo.get(SharedDataKeys.webInfoPort)

        return "$name,$os,$port"
    }
}