package com.oncemore.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Setter
@Component
public class UserCartInfo {
    private UUID userId;
    private UUID cartId;
}
