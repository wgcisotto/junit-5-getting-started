package com.wgcisotto.patient.intake.notifier;

public interface EmailNotifier {
    void sendNotification(String subject, String body, String address);
}
