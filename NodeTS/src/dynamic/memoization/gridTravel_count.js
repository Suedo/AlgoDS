
const travel = (m, n, memo = {}) => {

  const key = m + "," + n;
  if (key in memo) return memo[key];
  if (m === 1 && n === 1) return 1;
  if (m === 0 || n === 0) return 0;

  let down = travel(m - 1, n, memo);
  let right = travel(m, n - 1, memo);

  memo[key] = down + right;
  return memo[key];

}

console.log(travel(1, 1));
console.log(travel(2, 3));
console.log(travel(3, 2));
console.log(travel(3, 3));
console.log(travel(11, 11)); //184756
console.log(travel(18, 18));