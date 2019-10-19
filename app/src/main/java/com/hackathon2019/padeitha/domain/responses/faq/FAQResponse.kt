package com.hackathon2019.padeitha.domain.responses.faq

data class FAQResponse(
    val faqs: List<Faq>,
    val success: Boolean
)