# Kotlin playground

This is repo is done to test features of the Kotlin language and some spooky ideas in it.

As such there is the kotlinlang package, whose sub packages test Kotlin.

And the feature package, which test spooky ideas.

And for sure each exploration has associated unit tests to validate it :) 

## Kotlin lang

### Dataclass constructor returning a wrapper type (on validation failure)

When checking the parameters in a constructor, the common approach is to "just" throw exception.

But then it breaks [referential transparency](https://en.wikipedia.org/wiki/Referential_transparency), making it error-prone: easy to miss the validation bit.

Turns out [Kotlin Data classes](https://kotlinlang.org/docs/data-classes.html) makes it possible to have a constructor returning a wrapping type.

For example a nullable instance, being null if invalid.

This is explored in /src/main/kotlin/kotlinlang/dataclass/DataClassConstructorReturningNullable.

Pros:
- no more exception :)
- [copying](https://kotlinlang.org/docs/data-classes.html#copying) is still working (but throws...)

Cons:
- damned so much boilerplate :'(
 - however Domain Types, presented below and generalizing on the feature, help here: read further :)  

## Features

### Domain Types

What's that? 

Well, in Domain Driven Design, we aim for precise description of the business domain in the code.

Let say for example, in our domain, a name should be not empty and 100 characters at first.

Shall i use a dumb String for it? Well, how to make sure it's correct? 

So we go for a type, dedicated for this need, handling validation.

We could then simply use [Kotlin Data classes](https://kotlinlang.org/docs/data-classes.html).

Based on our experiment in ./src/main/kotlin/kotlinglang/dataclass, we know how to have the constructor return a wrapper type.

Let extend this to have a common implementation for the validating the string is in the expected range!

This is done in /src/main/kotlin/feature/domaintypes/basic/DomainName

Then, well, having a concept in the domain being a string of at least some length up to some other length is pretty common.

How can we factor this out so that we can create plenty of such types easily?

Well, looks like we are aiming for some kind of "archetype" named StringRange, so let's play with it in /src/main/kotlin/feature/domaintypes/archetypes/

Open question: can the operator fun invoke in DomainName be moved to StringRange?

