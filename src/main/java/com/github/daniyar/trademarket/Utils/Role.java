package com.github.daniyar.trademarket.Utils;

public enum Role implements io.javalin.security.Role { // enum with roles implementing javalin security pattern
    AUTHORIZED, ANONYMOUS, ADMIN;
}
