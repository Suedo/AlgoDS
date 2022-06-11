// https://leetcode.com/problems/course-schedule-ii/
// Topological sort of a DAG
// prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai
// thus, each [ai, bi] is an edge of a graph, going from bi to ai
let prereqs = [
  [1, 0],
  [2, 0],
  [3, 1],
  [3, 2]
]

// has a cycle
let prereqs2 = [
  [1, 0],
  [2, 0],
  [3, 1],
  [3, 2],
  [4, 0],
  [0, 4]
]

const buildGraph = (prereqs) => {
  let graph = {}
  for (const edge of prereqs) {
    let [b, a] = edge;

    if (!(a in graph)) graph[a] = []
    if (!(b in graph)) graph[b] = []

    graph[a].push(b)
  }
  console.log(`graph: ${JSON.stringify(graph)}`);
  return graph;
}

// recursive directed graph
const dfs = (graph, source, white = {}, grey = {}, black = {}) => {
  // checking if visited not needed in normal DAG dfs, but in topsort, prevents retaking of course
  // also, if a node is visited while it is in the grey set, it means there is a cycle
  // console.log(white, grey, black);
  if (source in black) return { "cycle": false, "value": [] };
  if (source in grey) return { "cycle": true, "value": null };

  // not in grey or black, so in white, pop from white, and push to grey
  grey[source] = white[source] // copied as value, not as pointer
  delete white[source]

  let result = []
  for (const neighbor of graph[source]) {
    let { cycle, value } = dfs(graph, neighbor, white, grey, black)
    if (cycle) return { "cycle": true, "value": null };
    result.push(...value)
  }

  // all children/neihbors processed, pop from grey, push to black
  black[source] = grey[source];
  delete grey[source];

  result.push(source); // return based traversal
  return { "cycle": false, "value": result };
}

const topologicalSort = (graph) => {

  /*
    A topological sort can only be done on a DAG, ie NO CYCLES
    if cycle found, return empty
    https://www.youtube.com/watch?v=rKQaZuoUR4M
    white   = {} :: list of vertices not yet visited
    gray    = {} :: list of vertices being vistied in this round of DFS
    black   = {} :: list of vertices completely explored, ie, all children completely explored
  */

  let white = {}, grey = {}, black = {};
  for (let node in graph) white[node] = true

  let topsorts = [], hasCycle = false;
  for (let node in graph) {
    // nodes are array index based, +node ensures it's read as a number
    // also not passing grey or black here as we are init-ing a new DFS from 'node'
    let stack = dfs(graph, +node, white)
    // console.log(node, stack);
    let { cycle, value } = stack
    topsorts.push(value)
    hasCycle = hasCycle || cycle;
  }

  console.log(`Cycle found: ${hasCycle}, topSorts:`);
  console.log(topsorts);
}

console.log(topologicalSort(buildGraph(prereqs)));

/*
Difference between 'obj[x]' vs 'x in obj':
let obj = { '0': undefined, '4': undefined }

x[0] // undefined <-- bad! even though 0 is present in the obj, we get false
0 in x // true <-- this is what we want

*/