const connectedComponents = (graph) => {
  let visited = {};
  let components = []

  for (const node in graph) {
    if (!visited[node]) {
      let e = dfs_explore(graph, node, visited)
      console.log(`${node}: explored components: ${e}`);
      components.push(e)
    }
  }

  return { "components": components, "count": components.length };
}

//undirected, recursive
const dfs_explore = (graph, source, visited) => {

  if (graph[source].length === 0) return [source]   // basecase 1: reached end of recursion 
  if (visited[source]) return [];                   // basecase 2: already processed, skip

  visited[source] = true; // in recursive, we mark visited before processing

  let result = []
  for (const neighbor of graph[source]) {
    let x = dfs_explore(graph, neighbor, visited)   // each substep of this recursion
    result.push(...x)                               // collect substep results
  }
  result.push(source)                               // combine with this step
  console.log(`\tcurrent: ${source}, result: ${result}`);
  return result
}

// undirected, iterative
const bfs_explore = (graph, source, visited) => {

  const queue = [source]; // initiate datastructure
  let result = [] // this will hold explored result

  while (queue.length > 0) {
    let current = queue.shift();                                                    // start: pull one item from DS
    for (const neighbor of graph[current]) {
      console.log(`current: ${current}, neighbors: ${graph[current]}`);
      if (!visited[neighbor]) queue.push(neighbor)                                  // process item
    }
    // done processing, mark visited, collect to result.
    visited[current] = true;                                                        // in iterative, we mark visited AFTER processing
    result.push(current)                                                            // collect processed result
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


/*
------------ dfs_explore: recursive ------------
        current: 9, result: 9
        current: 6, result: 6
        current: 7, result: 6,7
        current: 8, result: 9,6,7,8
        current: 2, result: 9,6,7,8,2
        current: 1, result: 9,6,7,8,2,1
1: explored components: 9,6,7,8,2,1
        current: 4, result: 4
        current: 3, result: 4,3
3: explored components: 4,3
{ components: [ [ 9, 6, 7, 8, 2, '1' ], [ 4, '3' ] ], count: 2 }

------------ bfs_explore: iterative ------------
current: 1, neighbors: 2
current: 2, neighbors: 1,8  
current: 2, neighbors: 1,8  
current: 8, neighbors: 9,7,2
current: 8, neighbors: 9,7,2
current: 8, neighbors: 9,7,2
current: 9, neighbors: 8
current: 7, neighbors: 6,8
current: 7, neighbors: 6,8
current: 6, neighbors: 7
        1, result: 1,2,8,9,7,6
1: explored components: 1,2,8,9,7,6
current: 3, neighbors: 4
current: 4, neighbors: 3
        3, result: 3,4
3: explored components: 3,4
{ components: [ [ '1', 2, 8, 9, 7, 6 ], [ '3', 4 ] ], count: 2 }

*/