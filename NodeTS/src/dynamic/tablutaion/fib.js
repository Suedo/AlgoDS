const fib = (num) => {

  // fib is a sum, so init value == 0
  const table = new Array(num + 1).fill(0)
  table[1] = 1; // 0,1 are the initial seed values of fib. 0 is already filled, now filling 1
  // console.log(table);

  for (let i = 2; i <= num; i++) {
    table[i] = table[i - 2] + table[i - 1] // we have seeded the first two, so iterate from 3rd value
  }

  // console.log(table);
  return table[num];

}


console.log(fib(6));
console.log(fib(50)); 