// return true if 'this' is prefix of the 'other' string 
String.prototype.isPrefixOf = function (other) {
  return other.indexOf(this) === 0
}
/* Returns True/False if a given word can be constructed from the array of chunks provided */
const canConstruct = (givenWord, chunkBank, memo = {}) => {

  console.log(`start>>> ${givenWord}, memo: ${JSON.stringify(memo)}`);

  if (givenWord === "") return true; // positive basecase, str can be constructed

  let applicablePrefixes = chunkBank.filter(each => each.isPrefixOf(givenWord))
  if (applicablePrefixes.length === 0) return false; // cannot be constructed

  if (givenWord in memo) return memo[givenWord];
  // "abcd".slice("ab".length) == "cd"

  result = false;
  // start work for this recursion step
  for (const prefix of applicablePrefixes) {
    // branch out
    let remainderStr = givenWord.slice(prefix.length);
    let r = canConstruct(remainderStr, chunkBank, memo)

    // combine result from each branch
    result = result || r;
  }

  // return final result value
  memo[givenWord] = result;
  console.log(`end<<< ${givenWord}, memo: ${JSON.stringify(memo)}`);
  return memo[givenWord];
}


console.log(canConstruct("abcdef", ["a", "ab", "c", "def", "abcd"]));