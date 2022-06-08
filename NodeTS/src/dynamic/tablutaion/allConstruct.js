// All possible ways to generate given string from an array of provided words 

const allConstruct = (targetWord, wordBank) => {

  // [] - denotes empty , no possible combinations available
  // [[]] - denotes some combination available
  let table = new Array(targetWord.length + 1).map(_ => [])
  table[0] = [[]] // possible was to construct a blank string: none, take no word from word bank


  for (let i = 0; i < targetWord.length; i++) {
    console.log(printTable(table));

    let remainder = i === 0 ? targetWord : targetWord.slice(i);
    let applicablePrefixes = wordBank.filter(each => each.isPrefixOf(remainder))
    if (applicablePrefixes.length === 0) continue; //skip, as no prefix found

    console.log(`i: ${i}, remainder: ${remainder}, prefixes: ${applicablePrefixes}`);
    for (let j = 0; j < applicablePrefixes.length; j++) {
      let result;
      let thisPrefix = applicablePrefixes[j]

      // result always a list of combinations, ie, format : [[...]]
      if (table[i]) result = table[i].map(each => [...each, thisPrefix])
      else result = [[thisPrefix]]

      let currentWord = targetWord.slice(0, i) + thisPrefix
      console.log(`currWord: ${currentWord}, result: ${JSON.stringify(result)}`);
      if (table[currentWord.length]) {
        // if combination exists, add each result combination to it
        table[currentWord.length].push(...result)
      } else {
        // if no previous combination found, set this result as the new set of combinations
        table[currentWord.length] = result;
      }

    }

  }

  return table[targetWord.length]

}

// return true if 'this' is prefix of the 'other' string 
String.prototype.isPrefixOf = function (other) {
  return other.indexOf(this) === 0
}

const printTable = table => table.map(each => each ? JSON.stringify(each) : "null").join(" - ")

console.log(allConstruct("abcdef", ["ab", "abc", "cd", "def", "abcd", "ef", "c"]));

/*

console.log(allConstruct("abcdef", ["ab", "abc", "cd", "def", "abcd", "ef", "c"]));

$ node allConstruct.js 
[[]] -  -  -  -  -  - 
i: 0, remainder: abcdef, prefixes: ab,abc,abcd
currWord: ab, result: [["ab"]]
currWord: abc, result: [["abc"]]
currWord: abcd, result: [["abcd"]]
[[]] -  - [["ab"]] - [["abc"]] - [["abcd"]] -  -
[[]] -  - [["ab"]] - [["abc"]] - [["abcd"]] -  -
i: 2, remainder: cdef, prefixes: cd,c
currWord: abcd, result: [["ab","cd"]]
currWord: abc, result: [["ab","c"]]
[[]] -  - [["ab"]] - [["abc"],["ab","c"]] - [["abcd"],["ab","cd"]] -  -
i: 3, remainder: def, prefixes: def
currWord: abcdef, result: [["abc","def"],["ab","c","def"]]
[[]] -  - [["ab"]] - [["abc"],["ab","c"]] - [["abcd"],["ab","cd"]] -  - [["abc","def"],["ab","c","def"]]
i: 4, remainder: ef, prefixes: ef
currWord: abcdef, result: [["abcd","ef"],["ab","cd","ef"]]
[[]] -  - [["ab"]] - [["abc"],["ab","c"]] - [["abcd"],["ab","cd"]] -  - [["abc","def"],["ab","c","def"],["abcd","ef"],["ab","cd","ef"]]
[
  [ 'abc', 'def' ],
  [ 'ab', 'c', 'def' ],
  [ 'abcd', 'ef' ],
  [ 'ab', 'cd', 'ef' ]
]
 */