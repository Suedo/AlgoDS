package _2024.stackQ

import kotlin.math.max

// https://leetcode.com/problems/valid-parentheses/description/
fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()
    val matchingParenthesis = mapOf(')' to '(', '}' to '{', ']' to '[')

    for (char in s) {
        when (char) {
            '(', '{', '[' -> stack.addLast(char)
            ')', '}', ']' -> {
                if (stack.isEmpty() || stack.removeLast() != matchingParenthesis[char]) {
                    return false
                }
            }
        }
    }
    // if all brackets have been used, nothing should remain in the stack
    return stack.isEmpty()
}


// https://leetcode.com/problems/simplify-path/description/
fun simplifyPath(path: String): String {

    val parts = path.split("/")
        .filter { it.isNotEmpty() && it != "." } // consecutive '/'-s filtered out, along with '.', which is current directory

    val stack = ArrayDeque<String>()

    for (part in parts) {
        when (part) {
            ".." -> if (stack.isNotEmpty()) stack.removeLast()
            else -> stack.addLast(part)
        }
    }

    return "/" + stack.joinToString("/")
}


/**
 * This function calculates the maximum nesting depth of parentheses in a given string.
 * https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/
 * @param s The input string containing valid parentheses.
 * @return The maximum nesting depth of parentheses.
 */
fun maxNestingDepth(s: String): Int {

    // Fold the string to calculate the maximum depth
    return s.fold(Pair(0, 0)) { (maxDepth, currentDepth), char ->
        when (char) {
            '(' -> Pair(max(maxDepth, currentDepth + 1), currentDepth + 1)
            ')' -> Pair(maxDepth, currentDepth - 1)
            else -> Pair(maxDepth, currentDepth)
        }
    }.first
}

// Test the function with provided examples
fun main() {
    val example1 = "(1+(2*3)+((8)/4))+1"
    val example2 = "(1)+((2))+(((3)))"
    val example3 = "()(())((()()))"

    println(maxNestingDepth(example1)) // Output: 3
    println(maxNestingDepth(example2)) // Output: 3
    println(maxNestingDepth(example3)) // Output: 3
}
