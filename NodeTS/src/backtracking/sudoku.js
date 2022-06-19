let board = [
  ["5", "3", ".", ".", "7", ".", ".", ".", "."],
  ["6", ".", ".", "1", "9", "5", ".", ".", "."],
  [".", "9", "8", ".", ".", ".", ".", "6", "."],
  ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
  ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
  ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
  [".", "6", ".", ".", ".", ".", "2", "8", "."],
  [".", ".", ".", "4", "1", "9", ".", ".", "5"],
  [".", ".", ".", ".", "8", ".", ".", "7", "9"]
]

const BLANK = "." // Grid's non digit blank position representation

/* ---- Helper Methods ---- */
const numberInRow = (num, row, board) => {
  return board[row].includes(num);
}

const numberInCol = (num, col, board) => {
  for (let i = 0; i < 9; i++) { // sudoku board always 9x9
    const ithCol = board[i][col];
    if (ithCol === num) return true;
  }
  return false;
}

const numInSubGrid = (num, row, col, board) => {
  let sgRow = row - (row % 3) // subgrid start row
  let sgCol = col - (col % 3) // subgrid start col

  for (let row = sgRow; row < sgRow + 3; row++) {
    for (let col = sgCol; col < sgCol + 3; col++) {
      const elem = board[row][col];
      if (elem === num) return true;
    }
  }

  return false;
}

const isValid = (num, row, col, board) => {
  // prefilled board items are all strings, so convert num to string for matching
  let inRow = numberInRow(num, row, board);
  let inCol = numberInCol(num, col, board);
  let inSubGrid = numInSubGrid(num, row, col, board);
  return (!inRow && !inCol && !inSubGrid)
}

const numberifyBoard = (board) => {
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      board[row][col] = board[row][col] === BLANK
        ? 0
        : +board[row][col]
    }
  }
}

const stringifyBoard = (board) => {
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      board[row][col] = `${board[row][col]}` // input string, processing number, so finally convert all to string
    }
  }
}

/* ------- Actual Recursion -------- */
const dfs_sudoku = (board, row = 0, col = 0) => {

  if (row === 9) return true; // sudoku solved
  if (col === 9) return dfs_sudoku(board, row + 1, 0) // next row; recursive matrix/grid traversal
  if (board[row][col] !== 0) return dfs_sudoku(board, row, col + 1) // skip pre-filled col


  // a blank position on the board: try all valid numbers
  for (let numToTry = 1; numToTry < 10; numToTry++) {
    if (isValid(numToTry, row, col, board)) {
      board[row][col] = numToTry; // guess; + add as a string
      let res = dfs_sudoku(board, row, col + 1) // recurse
      if (!res)
        board[row][col] = 0 // guess didnt solve, reset and try next number
      else return true
    }
  }
  return false; // tried every possible number, didnt solve, backtrack
}

numberifyBoard(board)
dfs_sudoku(board)
stringifyBoard(board)
console.log(board);

/*
------------- leetcode submission results -------------------
Runtime: 100 ms, faster than 95.36% of JavaScript online submissions for Sudoku Solver.
Memory Usage: 43.3 MB, less than 71.94% of JavaScript online submissions for Sudoku Solver.

ie: number comparisons are fater, so it pays off to:
1. first convert the board to Numbers
2. do the work
3. finally convert back to strings

instead of working on a string based board from the start
---------------
Solved sudoku
---------------
[
  [
    '5', '3', '4',
    '6', '7', '8',
    '9', '1', '2' 
  ],
  [
    '6', '7', '2',
    '1', '9', '5',
    '3', '4', '8' 
  ],
  [
    '1', '9', '8',
    '3', '4', '2',
    '5', '6', '7' 
  ],
  [
    '8', '5', '9',
    '7', '6', '1',
    '4', '2', '3' 
  ],
  [
    '4', '2', '6',
    '8', '5', '3',
    '7', '9', '1' 
  ],
  [
    '7', '1', '3',
    '9', '2', '4',
    '8', '5', '6' 
  ],
  [
    '9', '6', '1',
    '5', '3', '7',
    '2', '8', '4' 
  ],
  [
    '2', '8', '7',
    '4', '1', '9',
    '6', '3', '5' 
  ],
  [
    '3', '4', '5',
    '2', '8', '6',
    '1', '7', '9' 
  ]
]
*/

/*
==================================================================
Alternative, slower Sudoku, where we work on s string board,
instead of converting to numberboard first


---------- code start: ---------- 

let board = [
  ["5", "3", ".", ".", "7", ".", ".", ".", "."],
  ["6", ".", ".", "1", "9", "5", ".", ".", "."],
  [".", "9", "8", ".", ".", ".", ".", "6", "."],
  ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
  ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
  ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
  [".", "6", ".", ".", ".", ".", "2", "8", "."],
  [".", ".", ".", "4", "1", "9", ".", ".", "5"],
  [".", ".", ".", ".", "8", ".", ".", "7", "9"]
]

const BLANK = "." // Grid's non digit blank position representation


const numberInRow = (num, row, board) => {
  return board[row].includes(num);
}

const numberInCol = (num, col, board) => {
  for (let i = 0; i < 9; i++) { // sudoku board always 9x9
    const ithCol = board[i][col];
    if (ithCol === num) return true;
  }
  return false;
}

const findSubGrid = (row, col) => {
  let rowPos = row - (row % 3)
  let colPos = col - (col % 3)
  return [rowPos, colPos]
}

const numIn3x3Grid = (num, row, col, board) => {
  let [sgRow, sgCol] = findSubGrid(row, col);

  for (let row = sgRow; row < sgRow + 3; row++) {
    for (let col = sgCol; col < sgCol + 3; col++) {
      const elem = board[row][col];
      if (elem === num) return true;
    }
  }

  return false;
}

const isValid = (num, row, col, board) => {
  // prefilled board items are all strings, so convert num to string for matching
  let inRow = numberInRow("" + num, row, board);
  let inCol = numberInCol("" + num, col, board);
  let inSubGrid = numIn3x3Grid("" + num, row, col, board);
  return (!inRow && !inCol && !inSubGrid)
}

const stringifyBoard = (board) => {
  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      board[row][col] = `${board[row][col]}` // input string, processing number, so finally convert all to string
    }
  }
}


const dfs_sudoku = (board, row = 0, col = 0) => {

  if (row === 9) return true; // sudoku solved
  if (col === 9) return dfs_sudoku(board, row + 1, 0) // next row
  if (board[row][col] !== BLANK) return dfs_sudoku(board, row, col + 1) // skip pre-filled col


  // a blank position on the board: try all valid numbers
  for (let numToTry = 1; numToTry < 10; numToTry++) {
    if (isValid(numToTry, row, col, board)) {
      board[row][col] = "" + numToTry; // guess; + add as a string
      let res = dfs_sudoku(board, row, col + 1) // recurse
      if (!res)
        board[row][col] = BLANK // guess didnt solve, reset and try next number
      else return true
    }
  }
  return false; // tried every possible number, didnt solve, backtrack
}

dfs_sudoku(board)
console.log(board);

// ------------- leetcode submission results -------------------
// Runtime: 185 ms, faster than 38.61% of JavaScript online submissions for Sudoku Solver.
// Memory Usage: 42.9 MB, less than 86.08% of JavaScript online submissions for Sudoku Solver.

// ie: this is a slow algo: uses less memory, but executes slowly
// ----------------------------------------------------------------------------
 */