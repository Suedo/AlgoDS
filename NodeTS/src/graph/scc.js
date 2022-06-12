let edges = [
  [0, 1],
  [1, 2],
  [2, 0],
  [2, 3],
  [3, 4],
  [4, 7],
  [4, 5],
  [5, 6],
  [6, 4],
  [6, 7]
]

const buildGraph = (edges) => {
  let graph = {};

  for (const edge of edges) {
    let [a, b] = edge;

    if (!(a in graph)) graph[a] = []
    if (!(b in graph)) graph[b] = []

    graph[a].push(b)
  }

  console.log("graph:");
  console.log(graph);
  return graph
}

const graphRev = (graph) => {
  let reverse = {}

  for (const node in graph) {
    for (const neighbor of graph[node]) {

      if (!(neighbor in reverse)) reverse[+neighbor] = []
      if (!(node in reverse)) reverse[+node] = []

      reverse[+neighbor].push(+node)
    }
  }
  console.log("reverse graph:");
  console.log(reverse);
  return reverse
}

// recursive dfs, ++ visited, as SCC means that cycles will be present
const dfs = (graph, source, visited = {}) => {

  if (source in visited) return [];
  visited[source] = true;

  let result = [];
  for (const neighbor of graph[source]) {
    result.push(...dfs(graph, neighbor, visited))
  }
  result.push(source); // return(backtracing) based dfs traversal
  return result;
}


/**
 * 1. do a return based DFS of a graph, keep the result in stack
 * 2. reverse the graph
 * 3. do return based DFS of reversed graph, in pop-order of the result in step #1
 */
const scc = (graph) => {

  let visited = {}, dfs_result = [];
  for (const node in graph) {
    dfs_result.push(...dfs(graph, node, visited))
  }
  console.log(`dfs traversal of graph:\n${dfs_result}`);

  let components = []
  visited = {} // reinit, reuse
  let reverseGraph = graphRev(graph)
  while (dfs_result.length > 0) {
    let node = +dfs_result.pop();
    let c = dfs(reverseGraph, node, visited);
    console.log(node, c);
    components.push(c)
  }

  return components;

}

let graph = buildGraph(edges);
console.log(scc(graph));



/*
graph:
{
  '0': [ 1 ],
  '1': [ 2 ],
  '2': [ 0, 3 ],
  '3': [ 4 ],
  '4': [ 7, 5 ],
  '5': [ 6 ],
  '6': [ 4, 7 ],
  '7': []
}
dfs traversal of graph:
7,6,5,4,3,2,1,0
reverse graph:
{
  '0': [ 2 ],
  '1': [ 0 ],
  '2': [ 1 ],
  '3': [ 2 ],
  '4': [ 3, 6 ],
  '5': [ 4 ],
  '6': [ 5 ],
  '7': [ 4, 6 ]
}
0 [ 1, 2, 0 ]
1 []
2 []
3 [ 3 ]
4 [ 5, 6, 4 ]
5 []
6 []
7 [ 7 ]
[ [ 1, 2, 0 ], [], [], [ 3 ], [ 5, 6, 4 ], [], [], [ 7 ] ]

-----------------
I have not guarded againts visited while 2nd dfs (with reversed graph)
hence some components have come as [], which is the return type when dfs(node) is done on a visited node
*/