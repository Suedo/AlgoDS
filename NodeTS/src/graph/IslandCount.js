const islandCount = (grid) => {

  let visited = {};
  let count = 0, rows = grid.length, cols = grid[0].length;
  for (let row = 0; row < rows; row++) {
    for (let col = 0; col < cols; col++) {
      if (dfs_explore(grid, row, col, visited)) count++;
    }
  }
  return count;
}

// recursive dfs on a grid, not adjList
const dfs_explore = (grid, row, col, visited) => {

  // basecase 1: bounds check
  let rows = grid.length, cols = grid[0].length;
  if (row < 0 || row >= rows) return false;
  if (col < 0 || col >= cols) return false;

  // basecase 2: visited check
  let key = row + ',' + col;
  if (visited[key]) return false;

  // basecase 3: land or water check
  if (grid[row][col] === 'W') return false;

  // if we reach here, then we are in happy scenario, ie, in uncharted land, and within bounds
  // so now we do dfs, and try to explore the extent of this land
  visited[key] = true;
  dfs_explore(grid, row - 1, col, visited) // left
  dfs_explore(grid, row + 1, col, visited) // right
  dfs_explore(grid, row, col - 1, visited) // above
  dfs_explore(grid, row, col + 1, visited) // below

  // dfs recursion has returned
  return true; // this step was success, nothing to combine

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