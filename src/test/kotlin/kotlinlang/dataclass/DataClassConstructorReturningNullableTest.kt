package kotlinlang.dataclass

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DataClassConstructorReturningNullableTest {

    @Test
    fun constructorReturnsEmptyWhenValidationError() {
        val constructed: DataClassConstructorReturningNullable? = DataClassConstructorReturningNullable("")

        assertTrue(constructed == null)
    }

    @Test
    fun constructorReturnInstanceWhenNoValidationError() {
        val constructed: DataClassConstructorReturningNullable? = DataClassConstructorReturningNullable("foo")

        assertTrue(constructed != null)
    }

    @Test
    fun copyWorks() {
        val foo: DataClassConstructorReturningNullable = DataClassConstructorReturningNullable("foo")!!

        val bar: DataClassConstructorReturningNullable = foo.copy(string = "bar")

        assertNotNull(bar)
    }

    @Suppress("MoveLambdaOutsideParentheses")
    @Test
    fun copyThrowsOnInvalidContent() {
        val foo: DataClassConstructorReturningNullable = DataClassConstructorReturningNullable("foo")!!

        assertThrows(IllegalArgumentException::class.java, { foo.copy(string = "") })
    }

}
