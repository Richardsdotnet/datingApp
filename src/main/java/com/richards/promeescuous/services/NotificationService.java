package com.richards.promeescuous.services;


import com.richards.promeescuous.dtos.requests.NotificationRequest;
import com.richards.promeescuous.dtos.responses.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest notificationRequest);
}