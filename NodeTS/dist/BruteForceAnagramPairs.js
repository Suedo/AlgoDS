// https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
/**
 * Brute Force Anagram check:
 * 1. S1, S2 => turn into a character array => Sort both => if same, they are anagrams
 * 2. Given the above checking logic, have a O(N*N) double-for-loop sliding window to go through all string combinations of (S1, S2)
 * 3. Given the above, From one Input Str, create all possible (S1, S2) substring pairs, and check if they are anagrams
 */
export const BruteForceAnagramPairs = (ip) => {
    const anagramMap = new Map();
    let count = 0;
    // use sliding window to get all substrings of ip
    for (let i = 0; i < ip.length; i++) {
        for (let j = i + 1; j <= ip.length; j++) {
            const sub = ip.substring(i, j);
            const sorted = sub.split("").sort().join("");
            const existingValue = anagramMap.get(sorted);
            if (!existingValue) {
                // new anagram
                anagramMap.set(sorted, 1);
            }
            else {
                // nC2 = (n*(n-1))/2 === Arithmetic Progression sum of first n natural numbers, starting from 1
                // count += existingValue; //  this hurts my head, especially the operation being on existingValue
                anagramMap.set(sorted, existingValue + 1);
            }
        }
    }
    anagramMap.forEach((value, key) => {
        count += (value * (value - 1)) / 2; // Number of ways of creating a pair from N items: N choose 2 == N*(N-1)/2
    });
    return count;
};
console.log(BruteForceAnagramPairs("dbcfibibcheigfccacfegicigcefieeeeegcghggdheichgafhdigffgifidfbeaccadabecbdcgieaffbigffcecahafcafhcdg"));
