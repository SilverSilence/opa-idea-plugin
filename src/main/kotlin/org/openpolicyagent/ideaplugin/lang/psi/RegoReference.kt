/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.lang.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.openpolicyagent.ideaplugin.lang.RegoIcons
import org.openpolicyagent.ideaplugin.lang.RegoUtil


class RegoReference(element: PsiElement, textRange: TextRange) : PsiReferenceBase<PsiElement>(element, textRange),
    PsiPolyVariantReference {
    private val key: String

    init {
        key = element.text.substring(textRange.startOffset, textRange.endOffset)
    }

    override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> {
        val project = myElement!!.project
        val rules = RegoUtil.findRules(project, key)
        val results = mutableListOf<ResolveResult>()
        for (rule in rules) {
            results.add(PsiElementResolveResult(rule))
        }
        return results.toTypedArray()
    }

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants(): Array<out Any> {
        val project = myElement!!.project
        val rules = RegoUtil.findRules(project)
        val variants = mutableListOf<LookupElement>()
        for (rule in rules) {
            if (rule.name != null && rule.name!!.isNotEmpty()) {
                variants.add(
                    LookupElementBuilder
                        .create(rule)
                        .withIcon(RegoIcons.OPA)
                        .withTypeText(rule.containingFile.name)
                )
            }
        }
        return variants.toTypedArray()
    }
}