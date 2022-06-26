// https://leetcode.com/problems/create-maximum-number/
// Leetcode HARD

/*

given: The relative order of the digits from the same array must be preserved.

Thus, the solution becomes:
1. find a combination of subsequences from the two arrays, such that
2. when combined, the result in the largest number possible


*/

/**
 * we want to find the maximum subsequence of length k from the given array
 * this is the same as 'highest_k' problem solved earlier, ie highest subsequence formed after removing k elements
 * arr: array of numbers
 * k: selection size
 */
const maxSubsequence = (arr, subsequenceSize) => {

  let k = arr.length - subsequenceSize;

  // from here on, it's 'largest subsequence removing k items', aka highest_k
  // similar to https://leetcode.com/problems/remove-k-digits/
  if (arr.length <= k) return [];

  let stack = [], top;
  stack.push(arr[0]);


  for (let i = 1; i < arr.length; i++) {
    const curr = arr[i];
    top = stack.length - 1;

    while (k > 0 && top >= 0 && curr > stack[top]) {
      stack.pop();
      k--, top--
    }

    stack.push(curr);
  }

  // if k not yet exhausted
  while (k > 0 && stack.length > 0) {
    stack.pop();
    k--;
  }

  return stack;
}

// const merge = (arr1, arr2) => {
//   console.log(`received: [${arr1}] and [${arr2}]`);
//   if (arr1.length === 0) return arr2;
//   if (arr2.length === 0) return arr1;

//   let rank = 0;
//   while (rank < arr2.length) {
//     if (arr2[rank] <= arr1[0]) break;
//     rank++;
//   }
//   console.log(`rank of ${arr1[0]} in ${arr2}: ${rank}`);
//   let merged = [...arr2.slice(0, rank), ...arr1, ...arr2.slice(rank)]
//   console.log(`merged: [${merged}]`);
//   return merged;
// }

const merge = (arr1, arr2) => {
  console.log(`received: [${arr1}] and [${arr2}]`);
  if (arr1.length === 0) return arr2;
  if (arr2.length === 0) return arr1;

  let result = [], idx = 0;
  //mergesort like


  return merged;
}

const maxNumber = (nums1, nums2, k) => {
  let result = [];

  for (let i = 0; i <= k; i++) {
    let j = k - i;
    console.log(`i: ${i}, j: ${j}`);
    result.push(merge(maxSubsequence(nums1, i), maxSubsequence(nums2, j)))
  }

  let allPossibleK = result.filter(e => e.length === k);
  console.log(allPossibleK);
  allPossibleK.sort((a, b) => +(b.join("")) - +(a.join(""))) // desc order sort
  return allPossibleK[0]

}


let nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], k = 5;
// console.log(maxNumber(nums1, nums2, k));
// console.log("-".repeat(60));
// nums1 = [3, 9], nums2 = [8, 9], k = 3
// console.log(maxNumber(nums1, nums2, k));

// console.log("-".repeat(60));
// nums1 = [6, 7], nums2 = [6, 0, 4], k = 5
// console.log(maxNumber(nums1, nums2, k));

console.log("-".repeat(60));
nums1 = [9, 1, 2, 5, 8, 3], nums2 = [3, 4, 6, 5], k = 5
console.log(maxNumber(nums1, nums2, k)); // w: o/p: [9,8,4,6,5], expected: [9,8,6,5,3]
