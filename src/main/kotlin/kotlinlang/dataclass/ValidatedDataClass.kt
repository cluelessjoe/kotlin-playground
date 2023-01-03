package kotlinlang.dataclass

@Suppress("DataClassPrivateConstructor")
data class ValidatedDataClass private constructor(val string: String) {
    init {
        if (!validate(string)) throw IllegalArgumentException()
    }

    companion object {
        private fun validate(string: String): Boolean {
            return string.isNotEmpty()
        }

        operator fun invoke(string: String): ValidatedDataClass? {
            return if (validate(string))
                ValidatedDataClass(string)
            else
                null
        }
    }
}