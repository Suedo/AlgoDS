// get edge list, with weight for each edge
// build Graph based on edge list
// each "edge" : node a, node b, weight w
const buildGraph = (edgeList, directed = true) => {
  let graph = {};
  let edgeWeights = {}

  for (const edge of edgeList) {
    let [a, b, w = 1] = edge; // if no weight provided, consider default weight of 1, ie unweighted

    // in both directed an undirected graphs, nodes anyway need to be present
    if (!(a in graph)) graph[a] = []
    if (!(b in graph)) graph[b] = []

    graph[a].push(b);
    if (!directed) graph[b].push(a);


    // assuming no cycles, only one way to go from node a to node b
    let e = a + "," + b;
    edgeWeights[e] = w;
  }

  return { graph, edgeWeights }
}

const edges = [
  ["a", "e", 1],
  ["a", "b", 2],
  ["b", "c", 2],
  ["c", "d", 2],
  ["c", "g", 4],
  ["d", "g", 1],
  ["d", "f", 1],
  ["e", "f", 4]
];
exports.buildGraph = buildGraph;