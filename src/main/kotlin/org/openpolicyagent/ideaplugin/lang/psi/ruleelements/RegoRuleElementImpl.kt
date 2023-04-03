/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.lang.psi.ruleelements

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import org.openpolicyagent.ideaplugin.lang.psi.RegoRule
import org.openpolicyagent.ideaplugin.lang.psi.ruleelements.RegoPsiImplUtil.Companion.getName
import org.openpolicyagent.ideaplugin.lang.psi.ruleelements.RegoPsiImplUtil.Companion.getNameIdentifier
import org.openpolicyagent.ideaplugin.lang.psi.ruleelements.RegoPsiImplUtil.Companion.setName

abstract class RegoRuleElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), RegoRuleElement {
    override fun getName(): String? {
        return getName(this as RegoRule)
    }

    override fun setName(newName: String): PsiElement {
        return setName(this as RegoRule, newName)
    }

    override fun getNameIdentifier(): PsiElement? {
        return getNameIdentifier(this as RegoRule)
    }
}