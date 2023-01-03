package kotlinlang.dataclass

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidatedDataClassTest {

    @Test
    fun constructorReturnsEmptyWhenValidationError() {
        val validatedDataClass: ValidatedDataClass? = ValidatedDataClass("")

        assertTrue(validatedDataClass == null)
    }

    @Test
    fun constructorReturnInstanceWhenNoValidationError() {
        val validatedDataClass: ValidatedDataClass? = ValidatedDataClass("foo")

        assertTrue(validatedDataClass != null)
    }

    @Test
    fun copyWorks() {
        val foo: ValidatedDataClass = ValidatedDataClass("foo")!!

        val bar: ValidatedDataClass = foo.copy(string = "bar")

        assertNotNull(bar)
    }

    @Suppress("MoveLambdaOutsideParentheses")
    @Test
    fun copyThrowsOnInvalidContent() {
        val foo: ValidatedDataClass = ValidatedDataClass("foo")!!

        assertThrows(IllegalArgumentException::class.java, {foo.copy(string = "") })
    }

}
