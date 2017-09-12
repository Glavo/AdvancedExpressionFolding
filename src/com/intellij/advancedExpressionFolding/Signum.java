package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Signum extends Function implements ArithmeticExpression {
    public Signum(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, "signum", operands);
    }
}
