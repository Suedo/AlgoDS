const travel = (m, n, memo = {}) => {

  const key = m + ":" + n;
  if (key in memo) return memo[key];
  if (m === 1 && n === 1) return [key]
  if (m === 0 || n === 0) return null;

  let pathsDown = travel(m - 1, n, memo);
  let pathsRight = travel(m, n - 1, memo);


  let result = []

  // console.log(key, pathsDown, pathsRight);
  if (pathsDown) result.push(pathsDown)
  if (pathsRight) result.push(pathsRight)

  newResult = result.flatMap(pathList => {
    if (pathList.length <= 1) return [key, pathList].join(" > ") // single path from key node to leaf
    return pathList.map(e => [key, e].join(" > ")) // multiple path from key node to leaf
  })

  memo[key] = newResult;

  // console.log(`\tmemo: ${JSON.stringify(memo)}`);
  // console.log(`\tkey: ${key}, result: ${JSON.stringify(memo[key])}`);
  return memo[key];
}


var t3 = travel(3, 3);
// console.log(JSON.stringify(t3, null, 2), t3.length);
console.log(t3);

/*
[
  '3:3 > 2:3 > 1:3 > 1:2 > 1:1',
  '3:3 > 2:3 > 2:2 > 1:2 > 1:1',
  '3:3 > 2:3 > 2:2 > 2:1 > 1:1',
  '3:3 > 3:2 > 2:2 > 1:2 > 1:1',
  '3:3 > 3:2 > 2:2 > 2:1 > 1:1',
  '3:3 > 3:2 > 3:1 > 2:1 > 1:1'
]
*/