import { Graph } from "./Graph.js";
export class ConnectedComponents {
    constructor(g) {
        this.g = g;
        this.components = new Map();
        this.compNumber = 0; // current component number being dfs-ed
        this.visited = new Map();
        this.dfs = (vertex) => {
            if (this.visited.get(vertex))
                return;
            this.visited.set(vertex, true);
            this.components.get(this.compNumber)?.push(vertex) ||
                this.components.set(this.compNumber, [vertex]);
            const neighbors = this.g.edgesOf(vertex);
            neighbors?.map(this.dfs);
        };
        this.findComponents = () => {
            const vs = this.g.vertices();
            for (let i = 0; i < vs.length; i++) {
                if (!this.visited.get(vs[i])) {
                    this.dfs(vs[i]);
                    this.compNumber++;
                }
            }
            return this.components;
        };
        this.g = g;
    }
}
const edges = [
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
const g = new Graph();
edges.forEach(([u, v]) => g.addEdge(u, v));
const comps = new ConnectedComponents(g).findComponents();
console.log(comps);
