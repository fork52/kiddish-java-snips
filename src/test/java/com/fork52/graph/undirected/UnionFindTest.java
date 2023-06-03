package com.fork52.graph.undirected;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class UnionFindTest {
    @Test
    public void testCreation(){
        UnionFind unionFind = new UnionFind(10);
        Assertions.assertDoesNotThrow(() -> new Exception());
    }

    @Test()
    public void testUnionSetValid(){
        int n = 10;
        UnionFind unionFind = new UnionFind(n);
        for(int i = 0; i < n; i++){
            unionFind.unionSet(0, i);
        }

        for(int i = 0; i < n; i++)
            Assertions.assertEquals(n, unionFind.getComponentSize(i));
    }
}
