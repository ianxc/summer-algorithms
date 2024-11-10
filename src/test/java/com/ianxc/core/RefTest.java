package com.ianxc.core;

import org.junit.jupiter.api.Test;

public class RefTest {
    @Test
    void testMutation() {
        var ref = new Ref<Integer>(3);
        ref.data = 4;
    }
}
