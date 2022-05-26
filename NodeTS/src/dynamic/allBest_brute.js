const bestSum = (num, arr) => {
  console.log(`start ==> num: ${num}`);

  // base cases
  if (num === 0) return [];
  if (num < 0) return null


  // work needed per non base-case step
  let result = [];
  for (const eachNum of arr) {
    remainder = num - eachNum;
    r = bestSum(remainder, arr)
    if (r) {
      result.push([...r, eachNum]);
    }
  }

  // we need to return null if nothing was found in this step
  // to stay in sync with base case negative scenario
  if (result.length === 0) {
    result = null;
  } else {
    result = result.map(e => e.join(" > "))
  }
  // section result found
  console.log(`\t\tkey: ${num}, result: ${JSON.stringify(result)}`);

  // return section result: brute force only
  return result;
}


// console.log(bestSum(8, [2]));
// console.log(bestSum(7, [5, 3, 4, 7])) // [7]
console.log(JSON.stringify(bestSum(8, [2, 3, 5]))) // [3,5]
// console.log(bestSum(8, [1, 4, 5])) // [4,4]
// console.log(bestSum(100, [1, 2, 5, 25])) // [25,25,25,25]
