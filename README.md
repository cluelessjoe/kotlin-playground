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

This is explored in [DataClassConstructorReturningNullable](/src/main/kotlin/kotlinlang/dataclass/DataClassConstructorReturningNullable.kt).

Pros:

- no more exception :)
- [copying](https://kotlinlang.org/docs/data-classes.html#copying) is still working (but throws...)

Cons:

- damned so much boilerplate :'(
  - however Domain Types, presented below and generalizing on the feature, help here: read further :)  

## Features

### Domain Types

#### Basic attempt

What's that?

Well, [Domain Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design) aims for precise description of the business domain in the code.

Let say for example, in our domain, a name should be not empty and 100 characters at max.

Shall i use a dumb String for it? Well, how to make sure it's correct? 

So we go for a type, dedicated for this need, handling validation.

Based on our experiment on [Data class](/src/main/kotlin/kotlinlang/dataclass), we know how to have a constructor returns a wrapper type.

This is done in [BasicDomainName](/src/main/kotlin/feature/domaintypes/basic/BasicDomainName.kt).

#### Production ready attempt

Then, well, having a concept in the domain being a string of at least some length up to some other length is pretty common.

How can we factor this out so that we can create plenty of such types easily?

Well, looks like we are aiming for some kind of "archetype" named [StringRange](/src/main/kotlin/feature/domaintypes/archetypes), and play with it!

Note the interface/super class tricky logic on value validation:

- making value an open val in StringRange, the super class, doesn't work: the init method in the super class looks at the value of the subclass, which isn't initialized at this point in time, resulting in a NullPointerException.
- making value a property in StringRange's body results in the same.

As such i had to resort to an interface, Valuable, to make sure my Domain Type, DomainName here, would have its parameter named value. Then i've to pass the value to the super class for its validation, without making it a val there.

Works but not as straightforward as an open val in the super class!

#### Arrow production ready attempt!

Returning a nullable value is nice, and better than throwing, but it's lacking in explaining why it failed.

A nice way to resolve this is to use [Kotlin Arrow](https://arrow-kt.io/docs/core/)'s [Validated](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-validated/) to allow [accumulating errors](https://arrow-kt.io/docs/patterns/error_handling/). 

What's accumulating errors? Simply that, when validating some input, often you don't want to stop the validation at the first error, but rather to collect them all.

This avoids loops of input/error/changing and allows exhaustive error messages :).

This is done in the [validated](/src/main/kotlin/feature/domaintypes/validated) package.

#### To consider

Open question: can the operator fun invoke in DomainName be somehow not repeated for each Domain Type?

Todo: improve the above example with a User(firstName, lastName, login, email) so that added value of Validated is more visible :)