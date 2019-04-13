package com.example.demo.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiverService {

    @Input("myInput")
    SubscribableChannel receiverMsg();
}
