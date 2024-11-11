package com.ianxc.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RefTest {

    @Test
    void testMutation() {
        var ref = new Ref<Integer>(3);
        ref.data = 4;
        assertThat(ref.get()).isEqualTo(4);
    }
}
