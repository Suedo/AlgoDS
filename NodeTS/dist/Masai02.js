export const calculateHeight = (heights) => {
    const visionArr = [1]; // vision of leftmost student
    for (let i = 1; i < heights.length; i++) {
        let vision = 0; // vision of the ith student
        for (let j = i - 1; j >= 0; j--) {
            if (parseInt(heights[j]) <= parseInt(heights[i])) {
                vision++;
            }
            else {
                break;
            }
        }
        vision++; // the + 1 at the end
        visionArr.push(vision);
    }
    console.log(visionArr.join(" "));
};
export const processData = (input) => {
    const [count, ...inputs] = input.split("\n").map((line) => line.trim());
    // inputs.filter((val, idx) => idx % 2 !== 0).map((val, idx) => console.log(val + " :: " + idx))
    inputs
        .filter((val, idx) => idx % 2 !== 0)
        .forEach((val, idx) => calculateHeight(val.split(" ")));
};
/*
function processData(input) {
    const [count, ...inputs] = input.split("\n").map((line) => line.trim());
    inputs.map((val, idx) => console.log(val, idx))
}


process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
    _input += input;
});

process.stdin.on("end", function () {
   processData(_input);
});
*/
