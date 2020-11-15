/**
 * Memoized recursive fibonacci
 * @param n the number whose fibonacci we have to find
 */
export const fib = (n) => {
    const memo = [0, 1];
    const loop = (m) => {
        if (m <= 1)
            return memo[m];
        if (!memo[m]) {
            const mthFib = loop(m - 1) + loop(m - 2);
            memo[m] = mthFib;
        }
        return memo[m];
    };
    return loop(n);
};
/**
 * Bottom up fibonacci, uses an array of size 2 only
 * @param n the number whose fibonacci we have to find
 */
export const fibBU = (n) => {
    const memo = [0, 1];
    for (let i = 2; i <= n; i++) {
        memo[i % 2] = memo[(i - 1) % 2] + memo[(i - 2) % 2];
    }
    console.log(memo);
    return memo[n % 2];
};
export const fibseries = (n) => {
    const memo = [0, 1];
    for (let i = 2; i <= n; i++) {
        memo[i] = memo[i - 1] + memo[i - 2];
    }
    return memo;
};
console.log(fibseries(40));
