package com.fork52.graph.undirected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnionFindTest {
    @Test
    void testCreation(){
        UnionFind unionFind = new UnionFind(10);
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test()
    void testUnionSetOneComponentValid(){
        int n = 10;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++){
            unionFind.unionSet(0, i);
        }

        for(int i = 0; i < n; i++)
            Assertions.assertEquals(n, unionFind.getComponentSize(i));

        // Check if the unionSet method returns false for any
        // further calls.
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                Assertions.assertFalse(unionFind.unionSet(0, i));
        }
    }

    @Test()
    void testUnionSetTwoSetsValid(){
        int n = 10;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++){
            unionFind.unionSet(i & 1, i);
        }

        for(int i = 0; i < n; i++)
            Assertions.assertEquals(n / 2, unionFind.getComponentSize(i));
    }
}
