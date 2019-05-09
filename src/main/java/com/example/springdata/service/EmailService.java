package com.example.springdata.service;

public interface EmailService {
    void send(String to, String title, String body);
}
