import _2024.stackQ.isValid
import _2024.stackQ.simplifyPath
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class StackQueueTest {

    companion object {
        @JvmStatic
        fun validParenthesisProvider(): Stream<Arguments> = Stream.of(
            Arguments.of("()", true),
            Arguments.of("()[]{}", true),
            Arguments.of("(]", false),
            Arguments.of("([)]", false),
            Arguments.of("{[]}", true),
            Arguments.of("", true), // Edge case: empty string
            Arguments.of("[", false), // Edge case: unbalanced single bracket
            Arguments.of("]", false)  // Edge case: unbalanced single bracket
        )

        @JvmStatic
        fun pathProvider(): Stream<Arguments> = Stream.of(
            Arguments.of("/home/", "/home"),
            Arguments.of("/home//foo/", "/home/foo"),
            Arguments.of("/home/user/Documents/../Pictures", "/home/user/Pictures"),
            Arguments.of("/../", "/"),
            Arguments.of("/.../a/../b/c/../d/./", "/.../b/d")
        )
    }

    @ParameterizedTest
    @MethodSource("validParenthesisProvider")
    fun `test isValid`(input: String, expected: Boolean) {
        assertEquals(expected, isValid(input))
    }

    @ParameterizedTest
    @MethodSource("pathProvider")
    fun `test simplifyPath`(input: String, expected: String) {
        assertEquals(expected, simplifyPath(input))
    }
}
