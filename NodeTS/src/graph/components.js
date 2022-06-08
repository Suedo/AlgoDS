const connectedComponents = (graph) => {
  let visited = {};
  let components = []

  for (const node in graph) {
    if (!visited[node]) {
      let e = bfs_explore(graph, node, visited)
      console.log(`${node}: explored components: ${e}`);
      components.push(e)
    }
  }

  return { "components": components, "count": components.length };
}

//undirected
const dfs_explore = (graph, source, visited) => {

  if (visited[source]) return [];

  visited[source] = true;   // in recursive, we mark visited before processing
  if (graph[source].length === 0) return [source]

  let result = []
  for (const neighbor of graph[source]) {
    let x = dfs_explore(graph, neighbor, visited)
    result.push(...x)
  }
  result.push(source)
  console.log(`\t${source}, result: ${result}`);
  return result
}

// undirected, iterative
const bfs_explore = (graph, source, visited) => {

  const queue = [source];

  let result = []
  while (queue.length > 0) {
    let current = queue.shift();
    for (const neighbor of graph[current]) {
      console.log(`current: ${current}, neighbors: ${graph[current]}`);
      if (!visited[neighbor]) queue.push(neighbor)
    }
    // mark visited and thus, add to explored list
    visited[current] = true;  // in iteravtive, we mark visited AFTER processing
    result.push(current)
  }
  console.log(`\t${source}, result: ${result}`);
  return result;

}


// undirected graph
const graph = {
  1: [2],
  2: [1, 8],
  3: [4],
  4: [3],
  6: [7],
  9: [8],
  7: [6, 8],
  8: [9, 7, 2]
}

console.log(connectedComponents(graph));