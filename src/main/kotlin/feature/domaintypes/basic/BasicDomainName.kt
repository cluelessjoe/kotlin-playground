package feature.domaintypes.basic


@Suppress("DataClassPrivateConstructor")
data class BasicDomainName private constructor(val value: String) {
    init {
        if (!validate(value)) throw IllegalArgumentException("Value '$value' is invalid")
    }

    companion object {
        private fun validate(string: String): Boolean {
            return string.isNotEmpty() && string.length < 100
        }

        operator fun invoke(string: String): BasicDomainName? {
            return if (validate(string))
                BasicDomainName(string)
            else
                null
        }
    }
}