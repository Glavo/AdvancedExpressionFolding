package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Ulp extends Function implements ArithmeticExpression {
    public Ulp(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, "ulp", operands);
    }
}
