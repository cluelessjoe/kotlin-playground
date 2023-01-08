package feature.domaintypes.archetypes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArchetypeDomainNameTest {

    @Test
    fun domainNameNullWhenProvidingEmptyString() {
        val constructed: ArchetypeDomainName? = ArchetypeDomainName("")

        assertNull(constructed)
    }

    @Test
    fun domainNameConstructedWhenProvidingNonEmptyString() {
        val value = "dd"

        val constructed: ArchetypeDomainName? = ArchetypeDomainName(value)

        assertNotNull(constructed)
        assertEquals(value, constructed!!.value)
    }

    @Test
    fun domainNameNullWhenProviding101Characters() {
        val value = "a".repeat(101)

        val domainName: ArchetypeDomainName? = ArchetypeDomainName(value)

        assertNull(domainName)
    }

    @Test
    fun copyThrowsOnInvalidContent() {
        val created = ArchetypeDomainName("de")!!
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
        val created: ArchetypeDomainName? = ArchetypeDomainName(value)

        val toString = created.toString()

        assertTrue(toString.contains(value))
        assertTrue(toString.contains(ArchetypeDomainName::class.java.simpleName))
    }
}