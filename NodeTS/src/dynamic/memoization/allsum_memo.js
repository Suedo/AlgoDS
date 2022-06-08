/* All possible combinations from given array that Sums up to the given number */
const allSum = (num, arr, memo = {}) => {
  // console.log(`start ==> num: ${num}, memo: ${JSON.stringify(memo)}`);

  // base cases
  if (num === 0) return [];
  if (num < 0) return null
  // memoization
  if (num in memo) return memo[num]


  // work needed per non base-case step
  let stepResult = [];
  for (const eachNum of arr) {
    remainder = num - eachNum;
    remainderSum = allSum(remainder, arr, memo)

    // check againts negative basecase, ie, remainderSum === null
    if (remainderSum) {
      // arriving here means solution was found, and recursion reached positive basecase : num === 0, and retuned []

      // Now, if recursion coming off a non-leaf node, remainderSum is an array, 
      // whose each element denotes recursed solutions for each child branch of this node
      if (remainderSum.length > 0) {
        // flatten out each child solution into a string
        flattenedChildSolutions = remainderSum.map(each => each.join(" > "));
        // join each child solution with this step's information , ie, `eachNum`
        combined = flattenedChildSolutions.map(each => [each, eachNum].join(" > "))
        stepResult.push(combined)
      } else { // positive basecase, remainderSum == []
        // arriving here means recursion coming off leaf node, and meeting positive basecase 
        stepResult.push([eachNum]) // so, we return only 
      }
    }
  }

  // we need to return null if nothing was found in this step
  // to stay in sync with base case negative scenario
  if (stepResult.length === 0) {
    stepResult = null;
  }
  // section result found
  // console.log(`\t\tkey: ${num}, result: ${JSON.stringify(result)}`);
  memo[num] = stepResult;
  console.log(`end ==> num: ${num}, memo: ${JSON.stringify(memo)}`);
  // return section result: brute force only
  return memo[num];
}


// console.log(bestSum(8, [2]));
// console.log(bestSum(7, [5, 3, 4, 7])) 
// console.log(allSum(8, [3, 2, 5]).flatMap(e => e)) 
console.log(allSum(8, [2, 4, 5]).flatMap(e => e)) // [ '2 > 2 > 2 > 4 > 2 > 2', '2 > 4 > 2', '2 > 2 > 4', '4 > 4' ]
// console.log(bestSum(100, [1, 2, 5, 25]))
