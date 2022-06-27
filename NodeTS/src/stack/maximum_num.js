// https://leetcode.com/problems/create-maximum-number/
// Leetcode HARD

/*

given: The relative order of the digits from the same array must be preserved.

Thus, the solution becomes:
1. find a combination of subsequences from the two arrays, such that
2. when combined, the result in the largest number possible


---------------------- SOLUTION STATS as per leetcode ----------------------------------------
Runtime: 1064 ms, faster than 37.50% of JavaScript online submissions for Create Maximum Number.
Memory Usage: 90.6 MB, less than 8.33% of JavaScript online submissions for Create Maximum Number.

This is a slow algo, need to find better ones
-----------------------------------------------------------------------------------------------

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

// is seqA greater than seqB
const isGreater = (seqA, seqB) => {

  let idxA = 0, idxB = 0;
  while (idxA < seqA.length || idxB < seqB.length) {
    if (idxB >= seqB.length) return true; // seqB over, but seqA still going
    if (idxA >= seqA.length) return false; // seqA over, but seqB still going
    if (seqA[idxA] > seqB[idxB]) return true;
    if (seqA[idxA] < seqB[idxB]) return false;
    idxA++, idxB++; // when both equal, simply continue to next
  }
}

const merge = (arr1, arr2) => {
  let result = new Array(arr1.length + arr2.length), idx1 = 0, idx2 = 0;

  for (let idxR = 0; idxR < result.length; idxR++) {
    if (idx1 >= arr1.length) result[idxR] = arr2[idx2++] // arr1 done, merge from arr2
    else if (idx2 >= arr2.length) result[idxR] = arr1[idx1++] // arr2 done, merge from arr1

    // we want highest number from merge, so descending order
    else if (arr1[idx1] > arr2[idx2]) result[idxR] = arr1[idx1++]
    else if (arr1[idx1] < arr2[idx2]) result[idxR] = arr2[idx2++]
    else if (isGreater(arr1.slice(idx1), arr2.slice(idx2))) result[idxR] = arr1[idx1++]
    else result[idxR] = arr2[idx2++]
  }

  console.log(`received: [${arr1}] and [${arr2}], Merged: [${result}]`);
  return result;
}


const maxNumber = (nums1, nums2, k) => {
  let result = [];

  for (let i = 0; i <= k; i++) {
    let j = k - i;
    console.log(`i: ${i}, j: ${j}`);
    result.push(merge(maxSubsequence(nums1, i), maxSubsequence(nums2, j)))
  }

  let allPossibleK = result.filter(e => e.length === k); // array of Num arrays
  console.log(allPossibleK.map(e => e.join("")));

  // allPossibleK.sort((a, b) => +(b.join("")) - +(a.join(""))) // desc order sort
  // ^^^ this kindof sort, based on Number value wont work, as the value is bigger than what 'Number' an hold
  // we can reuse our sequence comparison here, as it compares each position at a time
  let maxVal = [];
  for (let i = 0; i < allPossibleK.length; i++) {
    const curr = allPossibleK[i];
    if (isGreater(curr, maxVal)) maxVal = curr;
  }

  return maxVal;

}


console.log("-".repeat(60));
let nums1 = [3, 9], nums2 = [8, 9], k = 3
console.log(maxNumber(nums1, nums2, k));

console.log("-".repeat(60));
nums1 = [6, 7], nums2 = [6, 0, 4], k = 5
console.log(maxNumber(nums1, nums2, k));

console.log("-".repeat(60));
nums1 = [9, 1, 2, 5, 8, 3], nums2 = [3, 4, 6, 5], k = 5
console.log(maxNumber(nums1, nums2, k)); // w: o/p: [9,8,4,6,5], expected: [9,8,6,5,3]

console.log("-".repeat(60));
nums1 = [2, 5, 6, 4, 4, 0], nums2 = [7, 3, 8, 0, 6, 5, 7, 6, 2], k = 15
console.log(maxNumber(nums1, nums2, k)); // [7, 3, 8, 2, 5, 6, 4, 4, 0, 6, 5, 7, 6, 2, 0]
