package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Floor extends Function implements ArithmeticExpression {
    public Floor(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, "floor", operands);
    }
}
