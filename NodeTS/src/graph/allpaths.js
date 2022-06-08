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


const dfs = (graph, source, destination) => {
  console.log(source);

  if (source === destination) return [[destination]]

  let result = [], step, found = false;
  for (const neighbor of graph[source]) {
    step = dfs(graph, neighbor, destination)
    result.push(...step)
  }
  let combined = result.map(each => [...each, source])
  console.log(source, JSON.stringify(combined));
  return combined
}

dfs(graph, 'a', 'g')


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