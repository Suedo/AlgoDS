// Adjacency List Graph
// rough translation of Algs4@Princeton Graph.java to TS

import { DFS } from "./DFS.js";

// Original: https://algs4.cs.princeton.edu/41graph/Graph.java.html
export class Graph<T> {
  private adj: Map<T, T[]>;

  constructor(private vertices: number) {
    this.adj = new Map();
    this.vertices = vertices;
  }

  addEdge = (u: T, v: T) => {
    this.adj.get(u)?.push(v) || this.adj.set(u, [v]);
    this.adj.get(v)?.push(u) || this.adj.set(v, [u]);
  };

  getCount = () => this.vertices;
  degree = (vertex: T) => this.adj.get(vertex)?.length;
  edgesOf = (vertex: T) => this.adj.get(vertex);

  print = () => {
    // iterate over map, ES2017 i blv
    for (const [key, value] of this.adj.entries()) {
      console.log(`${key} : ${value}`);
    }
  };
}

// a main() of sorts
type Edge<T> = [T, T];
type Edges<T> = Edge<T>[];

const edges: Edges<number> = [
  [1, 2],
  [2, 3],
  [2, 4],
  [3, 4],
  [4, 5],
  [4, 6],
];

const vertices = 6;

const g = new Graph<number>(vertices);
edges.forEach(([u, v]) => g.addEdge(u, v));
g.print();
console.log(g.degree(4) + "\n\n");

new DFS<number>(g).go(1);

/*
1 : 2
2 : 1,3,4
3 : 2,4
4 : 2,3,5,6
5 : 4
6 : 4
*/
