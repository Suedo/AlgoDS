// https://www.hackerrank.com/challenges/two-strings/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

export const commonSubstring = (s1: string, s2: string): string => {
  const set1 = new Set<string>();
  // O(N)
  s1.split("").forEach((letter) => set1.add(letter));

  // O(N)
  const set2 = new Set<string>();
  s2.split("").forEach((letter) => set2.add(letter));

  // even 1 letter common is considered as a common substring
  for (const elem of set1.values()) {
    if (set2.has(elem)) {
      return "YES";
    }
  }

  return "NO";
};
