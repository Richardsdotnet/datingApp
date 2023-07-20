package com.richards.promeescuous.repositories;

import com.richards.promeescuous.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
