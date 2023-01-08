package feature.domaintypes.archetypes


@Suppress("DataClassPrivateConstructor")
data class ArchetypeDomainName private constructor(override val value: String) :
    StringRange(value, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH) {

    companion object {
        private const val DOMAIN_NAME_MIN_LENGTH = 1
        private const val DOMAIN_NAME_MAX_LENGTH = 100

        operator fun invoke(string: String): ArchetypeDomainName? {
            return if (validate(string, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH))
                ArchetypeDomainName(string)
            else
                null
        }
    }
}