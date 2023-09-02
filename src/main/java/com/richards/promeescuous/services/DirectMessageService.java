package com.richards.promeescuous.services;


import com.richards.promeescuous.dtos.requests.DirectMessageRequest;
import com.richards.promeescuous.dtos.responses.DirectMessageResponse;
import com.richards.promeescuous.dtos.responses.FindAllMessagesResponse;



public interface DirectMessageService {
    DirectMessageResponse send(DirectMessageRequest request, Long senderId, Long receiverId);

    FindAllMessagesResponse findMessagesByIds(long sender, long receiver);
}
