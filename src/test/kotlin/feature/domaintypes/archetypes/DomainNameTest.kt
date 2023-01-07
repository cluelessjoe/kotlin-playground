package feature.domaintypes.archetypes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DomainNameTest {

    @Test
    fun domainNameNullWhenProvidingEmptyString() {
        val constructed: DomainName? = DomainName("")

        assertNull(constructed)
    }

    @Test
    fun domainNameConstructedWhenProvidingNonEmptyString() {
        val value = "dd"
        val constructed: DomainName? = DomainName(value)

        assertNotNull(constructed)
        assertEquals(value, constructed!!.value)
    }

    @Test
    fun domainNameNullWhenProviding101Characters() {
        val value = "a".repeat(101)
        val domainName: DomainName? = DomainName(value)

        assertNull(domainName)
    }

    @Test
    fun toStringOutputsTheValueAndClassName() {
        val value = "valid"
        val created: DomainName? = DomainName(value)

        val toString = created.toString()

        assertTrue(toString.contains(value))
        assertTrue(toString.contains(DomainName::class.java.simpleName))
    }
}