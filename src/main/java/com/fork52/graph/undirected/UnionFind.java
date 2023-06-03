package com.fork52.graph.undirected;

import java.util.Arrays;

/**
 * Class for UnionFind.
 */
public class UnionFind {
    private Integer size;
    private int[] parent;
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public void setParent(int []parent) {
        this.parent = parent;
    }

    public UnionFind(){}

    public UnionFind(int size) {
        this.parent = new int[size];
        Arrays.fill(parent, -1);
    }

     private <T> void swap(T a, T b){
         T temp = a;
         a = b;
         b = temp;
     }

    /**
     * Find parent representative of `u`.
     * @param u
     *      Set representative
     * */
    public int findSet(int u){
        if(parent[u] < 0) return u;
        parent[u] = findSet(parent[u]);
        return parent[u];
    }

    /**
     * Union sets represented by nodes `u` and `v`.
     * Returns whether the sets were combined now.
     *
     * @param u
     *      Set representative 1
     * @param v
     *      Set representative 2
    * */
     public boolean unionSet(Integer u, Integer v) {
         Integer pu = findSet(u), pv = findSet(v);
         if (pu.equals(pv))
             return false;

         Integer su = -parent[pu], sv = -parent[pv];

         if (su > sv) {
             swap(su, sv);
             swap(pu, pv);
             swap(u, v);
         }

         parent[pu] = pv;
         parent[pv] -= su;
         return true;
     }


    /**
     * Find size of set to which `u` belongs.
     * @param u
     *      Set representative
     * */
     public int getComponentSize(int u){
         return -parent[findSet(u)];
     }
}
