package kotlinlang.dataclass

@Suppress("DataClassPrivateConstructor")
data class DataClassConstructorReturningNullable private constructor(val string: String) {
    init {
        if (!validate(string)) throw IllegalArgumentException()
    }

    companion object {
        private fun validate(string: String): Boolean {
            return string.isNotEmpty()
        }

        operator fun invoke(string: String): DataClassConstructorReturningNullable? {
            return if (validate(string))
                DataClassConstructorReturningNullable(string)
            else
                null
        }
    }
}