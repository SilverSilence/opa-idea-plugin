/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.lang.psi.ruleelements

import com.intellij.psi.PsiElement
import org.openpolicyagent.ideaplugin.lang.psi.RegoElementFactory
import org.openpolicyagent.ideaplugin.lang.psi.RegoRule
import org.openpolicyagent.ideaplugin.lang.psi.RegoTypes


class RegoPsiImplUtil {
    companion object {

        @JvmStatic
        fun getRuleHead(element: RegoRule): String? {
            val keyNode = element.node.findChildByType(RegoTypes.RULE_HEAD)
            return keyNode?.text
        }

        @JvmStatic
        fun getName(element: RegoRule): String? {
            return getRuleHead(element)
        }

        @JvmStatic
        fun setName(element: RegoRule, newName: String): PsiElement {
            val keyNode = element.node.findChildByType(RegoTypes.RULE_HEAD)
            if (keyNode != null) {
                val property = RegoElementFactory.createRule(element.project, newName)
                val newKeyNode = property.firstChild.node
                element.node.replaceChild(keyNode, newKeyNode)
            }
            return element
        }

        @JvmStatic
        fun getNameIdentifier(element: RegoRule): PsiElement? {
            val keyNode = element.node.findChildByType(RegoTypes.RULE_HEAD)
            return keyNode?.psi
        }
    }
}
