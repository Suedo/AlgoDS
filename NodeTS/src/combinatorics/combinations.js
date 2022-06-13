const combinations = (arr) => {

  if (arr.length === 0) return [[]]; // [] is a valid combination when nothing is provided, so return [[]]

  let first = arr[0];
  let rest = arr.slice(1);

  // each choice has 2 outputs: either keeping 'first' elem, or not
  // so we have a perfect binary choice tree, with two subtrees based on above decision
  // one subtree doesnt have 'first' elem, other has 'first' combined into it
  let combinedResult = []
  let r_combs = combinations(rest)

  r_combs.forEach(combi => {
    combinedResult.push(combi);             // combi not including 'first'
    combinedResult.push([...combi, first])  // combi including 'first'
  });

  console.log(`first: ${first}, rest: ${JSON.stringify(rest)}, r_combs: ${JSON.stringify(r_combs)}`);
  console.log(`combined: ${JSON.stringify(combinedResult)}\n----`);
  return combinedResult;
}


// https://youtu.be/NA2Oj9xqaZQ
let arr = ['a', 'b', 'c']
console.log(combinations(arr));

/*
--- Output ---
Notice that c gets printed first, as it comes last in recursion, before hitting basecase of empty array
---

first: c, rest: [], r_combs: [[]]
combined: [[],["c"]]
----
first: b, rest: ["c"], r_combs: [[],["c"]]
combined: [[],["b"],["c"],["c","b"]]
----
first: a, rest: ["b","c"], r_combs: [[],["b"],["c"],["c","b"]]
combined: [[],["a"],["b"],["b","a"],["c"],["c","a"],["c","b"],["c","b","a"]]
----
[
  [],
  [ 'a' ],
  [ 'b' ],
  [ 'b', 'a' ],
  [ 'c' ],
  [ 'c', 'a' ],
  [ 'c', 'b' ],
  [ 'c', 'b', 'a' ]
]
*/