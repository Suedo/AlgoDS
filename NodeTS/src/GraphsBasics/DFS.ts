import { Graph } from "./Graph.js";

export class DFS<T> {
  visited: Map<T, boolean>;

  constructor(private g: Graph<T>) {
    this.g = g;
    this.visited = new Map();
  }

  private dfs = (vertex: T) => {
    if (this.visited.get(vertex)) return; // already visited?
    this.visited.set(vertex, true);
    console.log(vertex);

    const neighbors = this.g.edgesOf(vertex);
    neighbors?.map(this.dfs);
  };

  go = (vertex: T) => this.dfs(vertex);
}
