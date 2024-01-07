package com.frandz.produit.service;

public interface EmailSender {
    void sendEmail(String toEmail, String body);
}
