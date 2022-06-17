// [1,3,0,2] ==> [".Q..","...Q","Q...","..Q."]
const printBoard_2 = (state, n) => {

  let board = new Array(n).fill().map(_ => new Array(n).fill("."))
  let rows = board.length;

  for (let row = 0; row < rows; row++) { // Each row will have only 1 queen
    let col = state[row];
    board[row][col] = 'Q'
  }

  // convert 2D array to 1D array, joining all elems of a row into a string
  let board1D = board.map(row => row.join(""))
  return board1D;
}

// just for fun, maybe a bit faster
const printBoard = (state) => {
  let n = state.length;
  let result = []
  for (const colValue of state) {
    let row = ".".repeat(colValue) + "Q" + ".".repeat(n - (colValue + 1))
    result.push(row)
  }
  return result;
}

const dfs_nqueen = (n, state = [], result = []) => {

  let lastQueenRow = state.length - 1;

  if (state.length > 1) { // nothing to compare unless atleast 2 queens placed on board
    // check for valid conditons
    let lastQueenCol = state[lastQueenRow] // col pos
    for (let prevQueenRow = 0; prevQueenRow < lastQueenRow; prevQueenRow++) {
      let prevQueenCol = state[prevQueenRow];
      if (prevQueenCol === lastQueenCol) return;

      let rowDiff = Math.abs(lastQueenRow - prevQueenRow);
      let colDiff = Math.abs(lastQueenCol - prevQueenCol);

      if (rowDiff === colDiff) return; // queens fall on a diagonal
    }
  }

  // base case
  if (state.length === n) {
    result.push([...state]);
    return;
  }

  for (let colPos = 0; colPos < n; colPos++) {
    state.push(colPos)
    dfs_nqueen(n, state, result)
    state.pop()
  }

  return result;
}

const solveNQueen = (n) => {
  return dfs_nqueen(n).map(printBoard)
}

console.log(solveNQueen(2));