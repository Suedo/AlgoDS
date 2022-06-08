import { Graph } from "./Graph.js";

export class ConnectedComponents<T> {
  private components: Map<number, T[]> = new Map();
  private compNumber = 0; // current component number being dfs-ed
  visited: Map<T, boolean> = new Map();

  constructor(private g: Graph<T>) {
    this.g = g;
  }

  private dfs = (vertex: T) => {
    if (this.visited.get(vertex)) return;
    this.visited.set(vertex, true);
    this.components.get(this.compNumber)?.push(vertex) ||
      this.components.set(this.compNumber, [vertex]);

    const neighbors = this.g.edgesOf(vertex);
    neighbors?.map(this.dfs);
  };

  findComponents = () => {
    const vs = this.g.vertices();
    for (let i = 0; i < vs.length; i++) {
      if (!this.visited.get(vs[i])) {
        this.dfs(vs[i]);
        this.compNumber++;
      }
    }
    return this.components;
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
  [17, 18],
  [19, 19],
  [23, 24],
  [24, 25],
  [24, 26],
];

const g = new Graph<number>();
edges.forEach(([u, v]) => g.addEdge(u, v));

const comps = new ConnectedComponents(g).findComponents();
console.log(comps);
