export class DFS {
    constructor(g) {
        this.g = g;
        this.size = 0;
        this.dfs = (vertex) => {
            if (this.visited.get(vertex))
                return;
            this.visited.set(vertex, true);
            this.size++;
            // console.log(`${vertex} ${this.size}`);
            const neighbors = this.g.edgesOf(vertex);
            neighbors?.map(this.dfs);
        };
        this.go = (vertex) => {
            this.dfs(vertex);
            return this.size;
        };
        this.g = g;
        this.visited = new Map();
    }
}
