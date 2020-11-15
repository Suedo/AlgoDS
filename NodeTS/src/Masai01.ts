/*
You are given name and marks of N different students in a hackerrank contest.
Your task is to write a program that makes leaderboard of the students under following conditions:
If two students get same marks they get same rank
The student placed next to the same marks students will get the rank skipping the intermediate ranks.
Refer to the sample test case for better understanding.

6

joy 45
rancho 45
chatur 32
virus 32
raju 30
farhan 28
*/

// function processData(input) {
//   //Enter your code here
// }

// process.stdin.resume();
// process.stdin.setEncoding("ascii");
// _input = "";
// process.stdin.on("data", function (input) {
//   _input += input;
// });

// process.stdin.on("end", function () {
//   processData(_input);
// });

export const processData = (input: string) => {
  //Enter your code here
  const [count, ...lines] = input.split("\n").map((line) => line.trim());
  lines.sort(
    (l1, l2) => parseInt(l2.split(" ")[1]) - parseInt(l1.split(" ")[1])
  );

  const marksMap = new Map();

  lines.forEach((line) => {
    const [name, score] = line.split(" ");
    const names = marksMap.get(score);
    if (names) {
      names.push(name);
    } else {
      marksMap.set(score, [name]);
    }
  });

  let rank = 1;
  for (let [key, value] of marksMap) {
    // console.log(key, value);
    value.sort();
    value.map((name: any) => console.log(`${rank} ${name}`));
    rank = rank + value.length;
  }
};
