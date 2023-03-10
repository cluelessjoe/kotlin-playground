package feature.domaintypes.basic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BasicDomainNameTest {

    @Test
    fun domainNameNullWhenProvidingEmptyString() {
        val created: BasicDomainName? = BasicDomainName("")

        assertNull(created)
    }

    @Test
    fun domainNameConstructedWhenProvidingNonEmptyString() {
        val created: BasicDomainName? = BasicDomainName("dd")

        assertNotNull(created)
    }

    @Test
    fun domainNameNullWhenProviding101Characters() {
        val value = "a".repeat(101)

        val created: BasicDomainName? = BasicDomainName(value)

        assertNull(created)
    }

    @Test
    fun copyThrowsOnInvalidContent() {
        val created = BasicDomainName("de")!!
        var exception: Exception? = null

        try {
            @Suppress("UnusedDataClassCopyResult")
            created.copy(value = "")
        } catch (e: Exception) {
            exception = e
        }

        assertNotNull(exception)
        assertEquals("Value '' is invalid", exception!!.message)
    }

    @Test
    fun toStringOutputsTheValueAndClassName() {
        val value = "valid"
        val created: BasicDomainName? = BasicDomainName(value)

        val toString = created.toString()

        assertTrue(toString.contains(value))
        assertTrue(toString.contains(BasicDomainName::class.java.simpleName))
    }
}