# Kotlin playground

This repo is done to test features of the Kotlin language and some spooky ideas in it.

As such there is:
- the kotlinlang package, whose sub packages test Kotlin,
- the feature package, which test spooky ideas.

And for sure each exploration has associated unit tests :) 

## Kotlin lang

### Dataclass constructor returning a wrapper type (on validation failure)

When checking the parameters in a constructor, the common approach is to "just" throw exception.

But then it breaks [referential transparency](https://en.wikipedia.org/wiki/Referential_transparency), making it error-prone: easy to miss the validation bit.

Turns out [Kotlin Data classes](https://kotlinlang.org/docs/data-classes.html) makes it possible to have a constructor returning a wrapping type.

For example a nullable instance, being null if invalid.

This is explored in [DataClassConstructorReturningNullable](/src/main/kotlin/kotlinlang/dataclass/DataClassConstructorReturningNullable).

Pros:
- no more exception :)
- [copying](https://kotlinlang.org/docs/data-classes.html#copying) is still working (but throws...)

Cons:
- damned so much boilerplate :'(
  - however Domain Types, presented below and generalizing on the feature, help here: read further :)  

## Features

### Domain Types

What's that? 

Well, [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design) aims for precise description of the business domain in the code.

Let say for example, in our domain, a name should be not empty and 100 characters at max.

Shall i use a dumb String for it? Well, how to make sure it's correct? 

So we go for a type, dedicated for this need, handling validation.

Based on our experiment on [Data class](/src/main/kotlin/kotlinglang/dataclass), we know how to have a constructor returns a wrapper type.

This is done in [BasicDomainName](/src/main/kotlin/feature/domaintypes/basic/BasicDomainName).

Then, well, having a concept in the domain being a string of at least some length up to some other length is pretty common.

How can we factor this out so that we can create plenty of such types easily?

Well, looks like we are aiming for some kind of "archetype" named [StringRange](/src/main/kotlin/feature/domaintypes/archetypes/), and play with it! 

Open question: can the operator fun invoke in DomainName be moved to StringRange?

TODO: look for some Validation class in Kotlin, most likely arrow, to have some proper error handling :)