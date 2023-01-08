package feature.domaintypes.validated

import arrow.core.Nel
import arrow.core.Validated


@Suppress("DataClassPrivateConstructor")
data class DomainName private constructor(override val value: String) :
    StringRange(value, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH) {

    companion object {
        private const val DOMAIN_NAME_MIN_LENGTH = 1
        private const val DOMAIN_NAME_MAX_LENGTH = 100

        operator fun invoke(string: String): Validated<Nel<StringRangeValidationError>, DomainName> =
            validate(string, DOMAIN_NAME_MIN_LENGTH, DOMAIN_NAME_MAX_LENGTH) { s -> DomainName(s) }

    }
}