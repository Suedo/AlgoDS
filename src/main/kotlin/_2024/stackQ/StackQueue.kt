package _2024.stackQ

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
    return stack.isEmpty()
}


// https://leetcode.com/problems/simplify-path/description/
fun simplifyPath(path: String): String {
    val parts = path.split("/").filter { it.isNotEmpty() && it != "." }
    val stack = ArrayDeque<String>()

    for (part in parts) {
        when (part) {
            ".." -> if (stack.isNotEmpty()) stack.removeLast()
            else -> stack.addLast(part)
        }
    }

    return "/" + stack.joinToString("/")
}