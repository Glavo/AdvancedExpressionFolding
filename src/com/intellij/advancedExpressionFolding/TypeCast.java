package com.intellij.advancedExpressionFolding;

import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class TypeCast extends Expr implements CastExpression {
    private final @NotNull
    Expr object;

    public TypeCast(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull Expr object) {
        super(element, textRange);
        this.object = object;
    }

    @NotNull
    public Expr getObject() {
        return object;
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document,
                                       @Nullable Expr parent) {
        return true;
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expr parent) {
        boolean dotAccess = document.getTextLength() > getTextRange().getEndOffset()
                && document.getText(TextRange.create(getTextRange().getEndOffset(), getTextRange().getEndOffset() + 1)).equals(".");
        FoldingGroup group = FoldingGroup.newGroup(TypeCast.class.getName() + (dotAccess ? "" : Expr.HIGHLIGHTED_GROUP_POSTFIX));
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        if (object.getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
            if (dotAccess) {
                descriptors.add(new FoldingDescriptor(element.getNode(),
                        TextRange.create(getTextRange().getStartOffset(),
                                object.getTextRange().getStartOffset()), group) {
                    @NotNull
                    @Override
                    public String getPlaceholderText() {
                        return "";
                    }
                });
                descriptors.add(new FoldingDescriptor(element.getNode(),
                                        TextRange.create(object.getTextRange().getEndOffset(),
                                                getTextRange().getEndOffset() + 1), group) {
                                    @NotNull
                                    @Override
                                    public String getPlaceholderText() {
                                        return ".";
                                    }
                                }
                );
            } else {
                descriptors.add(new FoldingDescriptor(element.getNode(),
                                        TextRange.create(getTextRange().getStartOffset(),
                                                object.getTextRange().getStartOffset()), group) {
                                    @NotNull
                                    @Override
                                    public String getPlaceholderText() {
                                        return ""; // TODO: It used to be  "~"
                                    }
                                }
                );
                descriptors.add(new FoldingDescriptor(element.getNode(),
                        TextRange.create(object.getTextRange().getEndOffset(),
                                getTextRange().getEndOffset()), group) {
                    @NotNull
                    @Override
                    public String getPlaceholderText() {
                        return "";
                    }
                });
            }
        } else {
            descriptors.add(new FoldingDescriptor(element.getNode(),
                    TextRange.create(getTextRange().getStartOffset(),
                            object.getTextRange().getStartOffset()), group) {
                @NotNull
                @Override
                public String getPlaceholderText() {
                    return ""; // TODO: It used to be  "~"
                }
            });
        }
        if (object.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, object.buildFoldRegions(object.getElement(), document, this));
        }
        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }

    @Override
    public boolean isHighlighted() {
        return true;
    }
}
