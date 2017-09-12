package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Remainder extends Operation implements ArithmeticExpression {
    public Remainder(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, "%", 100, operands);
    }
}
