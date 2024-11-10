package com.ianxc.list;

import org.junit.jupiter.api.Test;

import static com.ianxc.core.CoreUtil.println;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class SinglyLinkedListTest {
    @Test
    void testPushAndPop() {
        var xs = new SinglyLinkedList<Integer>();
        println(xs);

        assertSoftly(s -> {
            s.assertThat(xs.isEmpty()).isTrue();
            s.assertThat(xs.size()).isZero();
            s.assertThat(xs.get(0)).isEmpty();
            s.assertThat(xs.get(1)).isEmpty();
            s.assertThat(xs.get(-1)).isEmpty();
        });

        xs.pushFront(2);
        xs.pushFront(17);
        xs.pushFront(-1);
        println(xs);

        assertSoftly(s -> {
            s.assertThat(xs.size()).isEqualTo(3);
            s.assertThat(xs.peek()).contains(-1);
            s.assertThat(xs.get(0)).contains(-1);
            s.assertThat(xs.get(1)).contains(17);
            s.assertThat(xs.get(2)).contains(2);
            s.assertThat(xs.get(-1)).isEmpty();
            s.assertThat(xs.get(3)).isEmpty();
        });

        var p1 = xs.popFront();
        var p2 = xs.popFront();
        var p3 = xs.popFront();
        var p4 = xs.popFront();
        println(xs);

        assertSoftly(s -> {
            s.assertThat(p1).contains(-1);
            s.assertThat(p2).contains(17);
            s.assertThat(p3).contains(2);
            s.assertThat(p4).isEmpty();
            s.assertThat(xs.isEmpty()).isTrue();
            s.assertThat(xs.get(0)).isEmpty();
            s.assertThat(xs.get(1)).isEmpty();
            s.assertThat(xs.get(-1)).isEmpty();
        });
    }

    @Test
    void testDelete() {
        var xs = new SinglyLinkedList<Integer>();
        println(xs);

        var r1 = xs.delete(0);
        var r2 = xs.delete(-1);
        var r3 = xs.delete(1);

        assertSoftly(s -> {
            s.assertThat(r1).isEmpty();
            s.assertThat(r2).isEmpty();
            s.assertThat(r3).isEmpty();
        });

        xs.pushFront(2);

        var r4 = xs.delete(-1);
        var r5 = xs.delete(1);
        var r6 = xs.delete(0);

        assertSoftly(s -> {
            s.assertThat(r4).isEmpty();
            s.assertThat(r5).isEmpty();
            s.assertThat(r6).contains(2);
            s.assertThat(xs.isEmpty()).isTrue();
        });

        xs.pushFront(6);
        xs.pushFront(7);
        xs.pushFront(1);

        var r7 = xs.delete(1);
        println(xs);

        assertSoftly(s -> {
            s.assertThat(r7).contains(7);
            s.assertThat(xs.size()).isEqualTo(2);
            s.assertThat(xs.get(0)).contains(1);
            s.assertThat(xs.get(1)).contains(6);
        });

        xs.insert(1, 99);
        println(xs);
        xs.insert(1, 98);
        println(xs);
        xs.insert(1, 97);
        println(xs);
        xs.insert(2, 89);
        println(xs);
        xs.insert(3, 88);
        println(xs);

        assertSoftly(s -> {
            s.assertThat(xs.size()).isEqualTo(7);
            s.assertThat(xs)
                    .asString()
                    .isEqualTo("SinglyLinkedList[1 > 97 > 89 > 88 > 98 > 99 > 6 > *]");
        });
    }

}
