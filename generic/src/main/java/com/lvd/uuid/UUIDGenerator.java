package com.lvd.uuid;

import java.util.UUID;

public class UUIDGenerator {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }
}
