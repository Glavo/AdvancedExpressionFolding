package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Less extends Operation implements ComparingExpression {
    public Less(PsiElement element, TextRange textRange, List<Expr> operands) {
        super(element, textRange, "<", 18, operands);
    }
}
