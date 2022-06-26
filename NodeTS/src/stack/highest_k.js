const numArr2Str = (numArr) => {
  // since final num can be larger than what 'Number' in js can hold, we need to:
  // 1. combine into a string
  // 2. also, since we are working on strings, no need for arithmetic to remove effect of leading zeros

  let i = 0;
  while (i < numArr.length) {
    if (numArr[i] !== 0) break;
    i++;
  }

  // the whole array is of 0s, concatenate into a single zero
  if (i === numArr.length) return "0"

  let trimmedArr = numArr.slice(i);
  return trimmedArr.join("");

}


// OPPOSITE OF https://leetcode.com/problems/remove-k-digits/
const highestK = (numstr, k) => {


  let numArr = numstr.split("").map(n => +n) // convert each array letter into a num
  if (numArr.length === k) return 0; // if we remove all elems, then we get 0

  let stack = [], top = 0;
  stack.push(numArr[0])

  for (let i = 1; i < numArr.length; i++) {
    const curr = numArr[i];
    top = stack.length - 1;

    while (k > 0 && top >= 0 && curr > stack[top]) { // if k exhausted, simply skip
      stack.pop();
      k--; top--;
    }
    stack.push(curr);
  }

  // by this time, if k !== 0, it means we have found the correct sequence, but still some characters need removing
  // we remove from the end, as that is the least significant digit when converted to a number
  while (k > 0 && stack.length > 0) {
    stack.pop();
    k--;
  }

  return numArr2Str(stack);
}

// console.log(highestK('372181', 2)) // 7281                       
// console.log(highestK('1230987', 3)) // 3987
// console.log(highestK('1230987', 3)) // 3987
// console.log(highestK('1432219', 3)) // 4329
// console.log(highestK('1432219', 6)) // 9
// console.log(highestK('10200', 1)) // 1200
// console.log(highestK('10200', 2)) // 200
// console.log(highestK('10', 2)) // 0
// console.log(highestK('9', 1)) // 0
// console.log(highestK('112', 1)) // 12
// console.log(highestK('123', 1)) // 23
// console.log(highestK('321', 1)) // 32
console.log(highestK('3465', 2)) // 32