"use strict";
// Adjacency List Graph
class Graph {
    constructor(vertices) {
        this.addEdge = (u, v) => {
            this.adj.get(u)?.push(v) || this.adj.set(u, [v]);
            this.adj.get(v)?.push(u) || this.adj.set(v, [u]);
        };
        this.getCount = () => {
            return this.vertices;
        };
        this.degree = (vertex) => {
            return this.adj.get(vertex)?.length;
        };
        this.print = () => {
            // nice way to iterate over map, ES2017 i blv
            for (const [key, value] of this.adj.entries()) {
                console.log(`${key} : ${value}`);
            }
        };
        this.adj = new Map();
        this.vertices = vertices;
    }
}
const edges = [
    [1, 2],
    [2, 3],
    [2, 4],
    [3, 4],
    [4, 5],
    [4, 6],
];
const vertices = 6;
const g = new Graph(vertices);
edges.forEach(([u, v]) => g.addEdge(u, v));
g.print();
console.log(g.degree(4));
/*
1 : 2
2 : 1,3,4
3 : 2,4
4 : 2,3,5,6
5 : 4
6 : 4
*/
