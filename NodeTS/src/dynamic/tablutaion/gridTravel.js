// how many ways to travel a grid m,n from m,n position to 1,1
const gridTravel = (m, n) => {

  // arrays are 0 based, but grid locations are 1 based
  // also for tabulation method, we take 1 size extra

  // also, grid travel is a sum based count problem, so initial values == 0

  const table = new Array(m + 1) // rows
    .fill() // initialize as empty
    .map(_ => Array(n + 1) // n columns in each row
      .fill(0) // fill each column of each row with 0 
    )

  table[1][1] = 1; // only 1 way to traverse a 1,1 size table. seed value
  console.log(table);

  for (let i = 1; i <= m; i++) {
    for (let j = 1; j <= n; j++) {
      console.log(`table[${i}][${j}] = ${table[i][j]}`);
      if (j < n) table[i][j + 1] += table[i][j]; // right
      if (i < m) table[i + 1][j] += table[i][j]; // down
    }
  }

  return table[m][n]
}

console.log(gridTravel(3, 3));

/*
A [3,3] grid in real life has positions starting from [1,1] to [3,3]
to mimic that natuarally, we need to create a [m+1, n+1] 2D array
which thus ranges from [0,0] to [m,n] positions. we ignore the 0 index rows and colums and calculate from [1,1] to [m,n]
thus to work with a [3,3] grid:
1. create a [4,4] grid, whose last position will be [3,3] as arrays index from 0
2. loop upto [3,3] and make sure to guard against out-of-bounds cases. max index position here is 3, so if any step can go beyond 3, check it
*/ 