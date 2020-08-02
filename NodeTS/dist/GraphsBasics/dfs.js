export class DFS {
    constructor(g) {
        this.g = g;
        this.dfs = (vertex) => {
            if (this.visited.get(vertex))
                return; // already visited?
            this.visited.set(vertex, true);
            console.log(vertex);
            const neighbors = this.g.edgesOf(vertex);
            neighbors?.map(this.dfs);
        };
        this.go = (vertex) => this.dfs(vertex);
        this.g = g;
        this.visited = new Map();
    }
}
