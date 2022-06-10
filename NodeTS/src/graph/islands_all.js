const islandCount = (grid) => {

  let visited = {};
  let count = 0, rows = grid.length, cols = grid[0].length, islands = [];
  for (let row = 0; row < rows; row++) {
    for (let col = 0; col < cols; col++) {
      let r = dfs_explore(grid, row, col, visited);
      if (r.length > 0) islands.push(r)
    }
  }
  return { "count": islands.length, "islands": JSON.stringify(islands) }
};


// recursive dfs on a grid, not adjList
const dfs_explore = (grid, row, col, visited) => {

  // basecase 1: bounds check
  let rows = grid.length, cols = grid[0].length;
  if (row < 0 || row >= rows) return [];
  if (col < 0 || col >= cols) return [];

  // basecase 2: visited check
  let key = row + ',' + col;
  if (visited[key]) return [];

  // basecase 3: land or water check
  if (grid[row][col] === 'W') return [];

  // if we reach here, then we are in happy scenario, ie, in uncharted land, and within bounds
  // so now we do dfs, and try to explore the extent of this land
  visited[key] = true;
  let result = [];
  result.push(...dfs_explore(grid, row - 1, col, visited))
  result.push(...dfs_explore(grid, row + 1, col, visited))
  result.push(...dfs_explore(grid, row, col - 1, visited))
  result.push(...dfs_explore(grid, row, col + 1, visited))

  // this step (even if none of the child recursion ones) was success, 
  // combine this step, ie 'key' with child results, and return
  result.push(key)
  return result;

}

const grid = [
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'L', 'W', 'W', 'W'],
  ['W', 'W', 'W', 'L', 'W'],
  ['W', 'W', 'L', 'L', 'W'],
  ['L', 'W', 'W', 'L', 'L'],
  ['L', 'L', 'W', 'W', 'W'],
];

console.log(islandCount(grid));