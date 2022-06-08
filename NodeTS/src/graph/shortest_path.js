const { buildGraph } = require("./build_graph")

// find shortest path between node a and b, where a is our starting node
const shortestPath = (edges, start, end, directed = true) => {

  const { graph, edgeWeights } = buildGraph(edges)
  console.log(graph);
  const visited = new Set()
  const queue = [[start, 0]]

  let shortestPathValue = Number.MAX_SAFE_INTEGER;

  while (queue.length > 0) {
    let [current, distance] = queue.shift();
    console.log(`current: ${current}, distance: ${distance}`);
    // console.log(`current: ${current}, distance: ${distance}, visited: ${[...visited].join("-")}`); // visited needed for undirected
    if (current === end) {
      shortestPathValue = distance < shortestPathValue ? distance : shortestPathValue
      console.log(`>> found path to ${end}, value: ${shortestPathValue}`);
    }

    for (const neighbor of graph[current]) {
      let currEdge = current + "," + neighbor;
      let currDistance = distance + edgeWeights[currEdge]

      if (!visited.has(neighbor)) {
        // process only non-visited, ie, new nodes
        queue.push([neighbor, currDistance])
        console.log(`\tneighbor: ${neighbor}  ::  thisEdge: ${currEdge}  :: newDistance: ${currDistance} :: queue: ${queue}`);
      }
    }

    directed ? "" : visited.add(current); // visited needed for undirected

  }

  // return 1 if path not found
  return shortestPathValue === Number.MAX_SAFE_INTEGER ? -1 : shortestPathValue;
}


const edges = [
  ["a", "e", 1],
  ["a", "b", 2],
  ["b", "c", 2],
  ["c", "d", 2],
  ["c", "g", 7],
  ["d", "g", 4],
  ["d", "f", 1],
  ["e", "f", 4],
  ["d", "h", 1],
  ["h", "i", 1],
  ["i", "g", 1]
];

console.log(shortestPath(edges, 'a', 'g')); // -> 9