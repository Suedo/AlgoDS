const { bfs, dfs } = require("../../graph/bfs_dfs")


const graph = {
  '2': [3, 6, 6, 7, 8, 9],
  '3': [6, 7, 9],
  '5': [6, 6, 7, 8, 9],
  '6': [7, 9],
  '7': [],
  '8': [9],
  '9': []
}



dfs(graph, 5)