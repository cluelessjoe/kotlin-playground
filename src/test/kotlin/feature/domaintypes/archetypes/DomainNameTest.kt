package feature.domaintypes.archetypes

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class DomainNameTest {

    @Test
    fun domainNameNullWhenProvidingEmptyString() {
        val constructed: DomainName? = DomainName("")

        assertNull(constructed)
    }

    @Test
    fun domainNameConstructedWhenProvidingNonEmptyString() {
        val constructed: DomainName? = DomainName("dd")

        assertNotNull(constructed)
    }

    @Test
    fun domainNameNullWhenProviding101Characters() {
        val value = "a".repeat(101)
        val DomainName: DomainName? = DomainName(value)

        assertNull(DomainName)
    }

    @Test
    fun toStringOutputsTheValueAndClassName() {
        val value = "valid"
        val created: DomainName? = DomainName(value)

        val toString = created.toString()

        Assertions.assertTrue(toString.contains(value))
        Assertions.assertTrue(toString.contains(DomainName::class.java.simpleName))
    }
}