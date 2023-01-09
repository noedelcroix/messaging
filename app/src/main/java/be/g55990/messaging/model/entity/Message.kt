package be.g55990.messaging.model.entity

data class Message(
    val from: String = "",
    val to: String = "",
    val message: String = "",
    val date: String = ""
)
