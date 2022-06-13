let arr = ['a', 'b', 'c', 'd', 'e', 'f']
let newEntry = 'X'

const permutations = (arr) => {
  if (arr.length === 0) return [[]];

  let first = arr[0];
  let rest = arr.slice(1);

  let permutation_result = []
  let perms = permutations(rest);
  console.log(`first: ${first}, rest: ${JSON.stringify(rest)}, perms: ${JSON.stringify(perms)}`);
  perms.forEach(each => {
    permutation_result.push(...combineIntoArray(first, each))
  })
  console.log(`final result: ${JSON.stringify(permutation_result)}\n--------`);
  return permutation_result;
}


const combineIntoArray = (entry, arr) => {

  let newArr = [entry, ...arr], result = [];

  // js will modify the same object each time
  // save a shallow copy to preserve current state of modifications
  result.push([...newArr]) // shallow copy
  for (let i = 0; i < newArr.length - 1; i++) {
    // swap i, i+1
    let temp = newArr[i];
    newArr[i] = newArr[i + 1];
    newArr[i + 1] = temp;
    result.push([...newArr])
  }
  return result;
}


// console.log(JSON.stringify(combineIntoArray('X', arr)));
// https://youtu.be/us0cYQXQpxg
console.log(JSON.stringify(permutations(['a', 'b', 'c'])));

/*

combineIntoArray('e', ['a', 'b', 'c', 'd'])
---
[ 'e', 'a', 'b', 'c', 'd' ]
[ 'a', 'e', 'b', 'c', 'd' ]
[ 'a', 'b', 'e', 'c', 'd' ]
[ 'a', 'b', 'c', 'e', 'd' ]
[ 'a', 'b', 'c', 'd', 'e' ]

console.log(JSON.stringify(permutations(['a', 'b', 'c'])));
---
first: c, rest: [], perms: [[]]
final result: [["c"]]
--------
first: b, rest: ["c"], perms: [["c"]]
final result: [["b","c"],["c","b"]]
--------
first: a, rest: ["b","c"], perms: [["b","c"],["c","b"]]
final result: [["a","b","c"],["b","a","c"],["b","c","a"],["a","c","b"],["c","a","b"],["c","b","a"]]
--------
[["a","b","c"],["b","a","c"],["b","c","a"],["a","c","b"],["c","a","b"],["c","b","a"]]

*/