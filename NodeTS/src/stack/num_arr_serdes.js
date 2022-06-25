// just for fun, but possibly less performant
const num2arr_str = (num) => {
  let str = `${num}`
  let numArr = str.split("").map(s => +s)
  console.log(`${numArr}`);
  return numArr
}

const num2Arr_q = (num) => {
  let q = [];
  let temp = num;
  while (temp > 0) {
    let rem = temp % 10
    q.unshift(rem); // instead of stack.push, and then a final stack.reverse()
    temp = Math.floor(temp / 10)
  }
  return q;
}

const num2Arr = (num) => {
  let stack = [];
  let temp = num;
  while (temp > 0) {
    let rem = temp % 10
    stack.push(rem);
    temp = Math.floor(temp / 10)
  }
  return stack.reverse();
}

const arr2num = (arr) => {
  let num = 0;
  for (let i = 0; i < arr.length; i++) {
    num = num + (arr[i] * Math.pow(10, (arr.length - 1 - i)))
  }
  return num;
}
