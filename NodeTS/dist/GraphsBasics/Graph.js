// Adjacency List Graph
// rough translation of Algs4@Princeton Graph.java to TS
import { DFS } from "./DFS.js";
// Original: https://algs4.cs.princeton.edu/41graph/Graph.java.html
export class Graph {
    constructor() {
        this.adj = new Map();
        this.addEdge = (u, v) => {
            this.adj.get(u)?.push(v) || this.adj.set(u, [v]);
            this.adj.get(v)?.push(u) || this.adj.set(v, [u]);
        };
        this.getSize = () => this.vertices().length;
        this.degree = (vertex) => this.adj.get(vertex)?.length;
        this.edgesOf = (vertex) => this.adj.get(vertex);
        this.vertices = () => {
            const vs = [];
            for (const elem of this.adj.keys())
                vs.push(elem);
            return vs;
        };
        this.print = () => {
            // iterate over map, ES2017 i blv
            for (const [key, value] of this.adj.entries()) {
                console.log(`${key} : ${value}`);
            }
        };
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
const g = new Graph();
edges.forEach(([u, v]) => g.addEdge(u, v));
g.print();
console.log(g.degree(4) + "\n\n");
console.log(new DFS(g).go(1));
/*
1 : 2
2 : 1,3,4
3 : 2,4
4 : 2,3,5,6
5 : 4
6 : 4
*/
