package com.example.demo.common.infrastructure;

import com.example.demo.common.service.port.UuidHolder;
import org.springframework.stereotype.Component;

@Component
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String random() {
        return "";
    }
}
