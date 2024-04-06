package org.example.springbootjwt.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:CREATE"),
    ADMIN_DELETE("admin:DELETE"),

    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:CREATE"),
    MANAGER_DELETE("management:DELETE")
    ;

    @Getter
    private final String permission;
}
