package feature.domaintypes.validated

import arrow.core.NonEmptyList
import arrow.core.Validated
import arrow.core.getOrElse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DomainNameTest {

    @Test
    fun domainNameInvalidWhenProvidingEmptyString() {
        val constructed: Validated<NonEmptyList<StringRangeValidationError>, DomainName> = DomainName("")

        assertTrue(constructed.isInvalid)
    }

    @Test
    fun domainNameConstructedWhenProvidingNonEmptyString() {
        val value = "dd"

        val constructed = DomainName(value)

        assertTrue(constructed.isValid)
        assertEquals(value, constructed.getOrElse { throw IllegalStateException() }.value)
    }

    @Test
    fun domainNameInvalidWhenProviding101Characters() {
        val value = "a".repeat(101)

        val constructed = DomainName(value)

        assertTrue(constructed.isInvalid)
    }

    @Test
    fun copyThrowsOnInvalidContent() {
        val foo = DomainName("foo").getOrElse { throw IllegalStateException() }
        var exception: Exception? = null

        try {
            @Suppress("UnusedDataClassCopyResult")
            foo.copy(value = "")
        } catch (e: Exception) {
            exception = e
        }

        assertNotNull(exception)
        assertEquals("Value '' has the following issue(s): below length of 1", exception!!.message)
    }

    @Test
    fun toStringOutputsTheValueAndClassName() {
        val value = "valid"
        val created = DomainName(value).getOrElse { throw IllegalStateException() }

        val toString = created.toString()

        assertTrue(toString.contains(value))
        assertTrue(toString.contains(DomainName::class.java.simpleName))
    }
}