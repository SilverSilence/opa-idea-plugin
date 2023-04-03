/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.ide.reference

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import org.openpolicyagent.ideaplugin.lang.psi.RegoReference

class RegoReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(PsiLiteralExpression::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<out PsiReference> {
                    val literalExpression = element as PsiLiteralExpression
                    val value = if (literalExpression.value is String) literalExpression.value as String else null
                    if (value != null && value.matches(Regex(".+\\{"))) {
                        val rule = TextRange(0, value.indexOf("{"))
                        return arrayOf<PsiReference>(RegoReference(element, rule))
                    }
                    return PsiReference.EMPTY_ARRAY
                }
            })
    }
}