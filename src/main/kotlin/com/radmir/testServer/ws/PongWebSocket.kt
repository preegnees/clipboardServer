package com.radmir.testServer.ws

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList




@Component
class PongWebSocket: TextWebSocketHandler(), WebSocketHandler {

    var sessions: MutableList<WebSocketSession> = mutableListOf()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        println("socket connection: $session")
        sessions.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
        println("message: ${message.payload}")
        for (webSocketSession in sessions) {
            if (webSocketSession.isOpen && session.id != webSocketSession.id) {
                webSocketSession.sendMessage(message)
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        println("close connection: $session")
    }
}