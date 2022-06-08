const graph = {
  a: ['e', 'b'],
  e: ['f'],
  b: ['c'],
  c: ['d', 'g'],
  d: ['g', 'f', 'h'],
  g: [],
  f: [],
  h: ['i'],
  i: ['g']
}

// iterative bfs
const bfs = (graph, start) => {
  const queue = [start]; // initiate the queue

  while (queue.length > 0) {
    let current = queue.shift(); // remove from head
    console.log(current);
    for (let neighbor of graph[current]) {
      queue.push(neighbor); // add to end
    }
  }
}

// iterative dfs
const dfs = (graph, start) => {
  const stack = [start]; // initiate the stack

  while (stack.length > 0) {
    let current = stack.pop(); // remove latest element
    console.log(current);
    for (const neighbor of graph[current]) {
      stack.push(neighbor) // add to latest
    }
  }
}

const dfs_r = (graph, source) => {
  console.log(source);
  for (const neighbor of graph[source]) {
    dfs_r(graph, neighbor)
  }
}

exports.bfs = bfs;
exports.dfs = dfs;

dfs_r(graph, 'a')


/*
{
  '2': [3, 6, 6, 7, 8, 9],
  '3': [6, 7, 9],
  '5': [6, 6, 7, 8, 9],
  '6': [7, 9],
  '7': [],
  '8': [9],
  '9': []
}
*/