// Hacker Rank link:
// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
const whatFlavors = (costs, totalMoney) => {
    const costsMap = new Map();
    // setting up costsMap
    costs.forEach((cost, idx) => {
        const arr = costsMap.get(cost);
        if (arr) {
            arr.push(idx);
        }
        else {
            costsMap.set(cost, [idx]);
        }
    });
    // console.log(costsMap);
    let result = [];
    for (let i = 0; i < costs.length; i++) {
        const c1 = costs[i];
        const c2 = totalMoney - c1;
        if (c1 === c2) {
            const x = costsMap.get(c1);
            if (x && x.length >= 2) {
                result = x.slice(0, 2).sort((a, b) => a - b);
                break;
            }
        }
        else {
            const [j, ...rest] = costsMap.get(c2) || [];
            if (j) {
                result = [i, j].sort((a, b) => a - b);
                break;
            }
        }
    }
    // show as 1 based indexed output
    console.log(result.map((a) => a + 1).join(" "));
};
// console.log(whatFlavors([1, 4, 5, 3, 2], 4));
console.log(whatFlavors("1 2 3 5 6".split(" ").map((a) => +a), 8));
// console.log(whatFlavors([4, 3, 2, 5, 7], 8))
