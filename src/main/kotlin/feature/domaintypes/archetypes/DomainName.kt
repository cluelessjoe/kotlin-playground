package feature.domaintypes.archetypes


@Suppress("DataClassPrivateConstructor")
data class DomainName private constructor(val value: String) :
    StringRange(value, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH) {

    companion object {
        private const val DOMAIN_NAME_MIN_LENGTH = 1
        private const val DOMAIN_NAME_MAX_LENGTH = 100

        operator fun invoke(string: String): DomainName? {
            return if (validate(string, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH))
                DomainName(string)
            else
                null
        }
    }
}