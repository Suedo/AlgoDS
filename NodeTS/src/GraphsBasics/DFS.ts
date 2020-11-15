import { Graph } from "./Graph.js";

export class DFS<T> {
  visited: Map<T, boolean>;
  size: number = 0;

  constructor(private g: Graph<T>) {
    this.g = g;
    this.visited = new Map();
  }

  private dfs = (vertex: T) => {
    if (this.visited.get(vertex)) return;
    this.visited.set(vertex, true);
    this.size++;
    // console.log(`${vertex} ${this.size}`);

    const neighbors = this.g.edgesOf(vertex);
    neighbors?.map(this.dfs);
  };

  go = (vertex: T) => {
    this.dfs(vertex);
    return this.size;
  };
}
