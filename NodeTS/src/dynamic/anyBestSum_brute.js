const bestSum = (num, arr, best = null) => {
  // console.log(`start ==> num: ${num}, memo: ${JSON.stringify(memo)}`);

  // base cases
  if (num === 0) return [];
  if (num < 0) return null


  // work needed per non base-case 
  // find the best for this section
  for (const eachNum of arr) {
    remainder = num - eachNum;
    r = bestSum(remainder, arr)
    if (r) {
      temp = [...r, eachNum]
      if ((best === null) || (best.length > temp.length)) {
        // console.log(`best: ${best}, temp: ${temp}`)
        best = temp;
      }
    }

    // section result found
    // console.log(`\t\tkey: ${num}, best: ${best}`);
  }

  // return section result: brute force only
  return best;
}


console.log(bestSum(8, [2]));
console.log(bestSum(7, [5, 3, 4, 7])) // [7]
console.log(bestSum(8, [2, 3, 5])) // [3,5]
console.log(bestSum(8, [1, 4, 5])) // [4,4]
// console.log(bestSum(100, [1, 2, 5, 25])) // [25,25,25,25]
