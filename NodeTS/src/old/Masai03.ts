import { rank, floorInclusive } from "./Helpers.js";
import { relative } from "path";

// Leboo buckets
// since JS doesnt provide proper array deleting mechanism
let buckets = [
  6,
  79,
  203,
  212,
  221,
  275,
  279,
  343,
  358,
  372,
  482,
  502,
  504,
  594,
  632,
  642,
  674,
  679,
  799,
  823,
  882,
];

let peoples = [29, 56, 87, 114, 120, 120, 480, 979];
let time = 0;
function showStep() {
  console.log("--------------------------------");
  console.log(buckets.join(" "));
  console.log(peoples.join(" "));
  console.log(time);
  console.log("--------------------------------");
}

function getMatchingBucket(capacity: number) {
  return floorInclusive(buckets, capacity);
}

while (buckets.length > 0) {
  // each round
  const peopleRemaining = peoples.length;

  // removing breaks index, workaround: iterate from back
  // https://stackoverflow.com/a/9882349/2715083
  for (let i = peopleRemaining - 1; i >= 0; i--) {
    const person = peoples[i];
    const bucket = getMatchingBucket(person);

    if (!bucket) {
      // no matching capacity bucket found for this person
      peoples.splice(i, 1);
      console.log(`👻 people: ${person}`);
    } else {
      // matching bucket found, and removing from list for next turn
      const [idx = -1, _] = rank(buckets, bucket);
      idx >= 0 && buckets.splice(idx, 1); // 0 is falsy in JS
      console.log(`💧 bucket: ${bucket} from index: ${idx}`);
    }
  }

  // all buckets assigned to matching capable people
  // if no buckets left, then this is the last trip, no return needed
  if (buckets.length == 0) time += 1;
  else time += 2;
}

console.log(`\n ⏱⏱⏱ Total time taken for trip: ${time}`);
