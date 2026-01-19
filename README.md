# rewrite-querydsl

OpenRewrite recipes used by the Moderne migration practice workshop to upgrade QueryDSL to 5 and align code with Spring Boot 4/Jakarta changes.

## Related workshop

This repository is referenced by the [Moderne migration practice repository](https://github.com/modernetraining/moderne-migration-practice), which is used by the [Spring Boot migration workshop](https://docs.moderne.io/hands-on-learning/spring-boot-migration/workshop-overview).

Module 5 uses these recipes to build a composite upgrade that pairs QueryDSL fixes with the Spring Boot migration.

## Recipes included

- `org.openrewrite.recipe.querydsl.UpgradeToQueryDsl5` (declared in `src/main/resources/META-INF/rewrite/rewrite.yml`)
- `org.openrewrite.recipe.querydsl.ChangeJPAQueryListToFetch`

## Build and install locally

Build and publish to your local Maven repo:

```bash
./gradlew publishToMavenLocal
# or
./mvnw install
```

Then install the recipe JAR with the Moderne CLI:

```bash
mod config recipes jar install org.openrewrite.recipe:rewrite-querydsl:0.1.0-SNAPSHOT
```

## Notes

This repository is purpose-built for the workshop. If you are looking for a general OpenRewrite recipe starter, use the [`rewrite-recipe-starter`](https://github.com/moderneinc/rewrite-recipe-starter) template instead.
