// Adjacency List Graph
// rough translation of Algs4@Princeton Graph.java to TS
// Original: https://algs4.cs.princeton.edu/41graph/Graph.java.html
class Graph<T> {
  adj: Map<T, T[]>;
  vertices: number;
  constructor(vertices: number) {
    this.adj = new Map();
    this.vertices = vertices;
  }

  addEdge = (u: T, v: T) => {
    this.adj.get(u)?.push(v) || this.adj.set(u, [v]);
    this.adj.get(v)?.push(u) || this.adj.set(v, [u]);
  };

  getCount = () => {
    return this.vertices;
  };

  degree = (vertex: T) => {
    return this.adj.get(vertex)?.length;
  };

  print = () => {
    // nice way to iterate over map, ES2017 i blv
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
console.log(g.degree(4));

/*
1 : 2
2 : 1,3,4
3 : 2,4
4 : 2,3,5,6
5 : 4
6 : 4
*/
