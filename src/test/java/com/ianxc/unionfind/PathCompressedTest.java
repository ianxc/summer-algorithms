package com.ianxc.unionfind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PathCompressedTest {
    @Test
    void testUpdateRootOnFind() {
        var unionFind = new PathCompressed(6);

        // end up with PathCompressed[size=6, root=[1, 2, 3, 4, 5, 5]].
        // unionFind.union(1, 0);
        // unionFind.union(2, 0);
        // unionFind.union(3, 0);
        // unionFind.union(4, 0);
        // unionFind.union(5, 0);

        unionFind.union(4, 5);
        unionFind.union(3, 4);
        unionFind.union(2, 3);
        unionFind.union(1, 2);
        unionFind.union(0, 1);
        assertThat(unionFind)
                .hasToString("PathCompressed[size=6, root=[0, 0, 1, 2, 3, 4]]");

        var root5 = unionFind.findCompressed(5);

        assertThat(root5).isEqualTo(0);
        assertThat(unionFind)
                .hasToString("PathCompressed[size=6, root=[0, 0, 0, 0, 0, 0]]");
    }
}
