package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class ShiftRightAssign extends Operation implements ArithmeticExpression {
    public ShiftRightAssign(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, ">>=", 300, operands);
    }
}
