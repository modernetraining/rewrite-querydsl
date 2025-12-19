package org.openrewrite.recipe.querydsl;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Preconditions;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.internal.lang.NonNullApi;
import org.openrewrite.java.JavaVisitor;
import org.openrewrite.java.MethodMatcher;
import org.openrewrite.java.search.UsesMethod;
import org.openrewrite.java.tree.J;

import java.util.Collections;

@NonNullApi
public class ChangeJPAQueryListToFetch extends Recipe {

    @Override
    public String getDisplayName() {
        return "Change JPAQuery list(..) to fetch()";
    }

    @Override
    public String getDescription() {
        return "Changes AbstractJPAQuery list(..) method calls to fetch() with no arguments in preparation for migration to JPAQueryFactory and QueryDSL 5.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        MethodMatcher matcher = new MethodMatcher("com.mysema.query.jpa.impl.AbstractJPAQuery list(..)");
        return Preconditions.check(new UsesMethod<>(matcher), new JavaVisitor<ExecutionContext>() {
            @Override
            public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, ExecutionContext ctx) {
                J.MethodInvocation m = (J.MethodInvocation) super.visitMethodInvocation(method, ctx);
                if (matcher.matches(m)) {
                    m = m.withName(m.getName().withSimpleName("fetch"))
                            .withArguments(Collections.emptyList());
                }
                return m;
            }
        });
    }
}