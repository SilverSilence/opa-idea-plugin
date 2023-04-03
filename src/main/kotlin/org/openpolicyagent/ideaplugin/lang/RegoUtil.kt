/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.lang

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.openpolicyagent.ideaplugin.lang.psi.RegoFile
import org.openpolicyagent.ideaplugin.lang.psi.RegoRule

object RegoUtil {
    /**
     * Searches the entire project for Rego language files with instances of the Rego-Rule with the given key.
     *
     * @param project current project
     * @param ruleName     to check
     * @return matching properties
     */
    fun findRules(project: Project, ruleName: String): List<RegoRule> {
        val result = mutableListOf<RegoRule>()
        val virtualFiles = FileTypeIndex.getFiles(RegoFileType, GlobalSearchScope.allScope(project))
        for (virtualFile in virtualFiles) {
            val simpleFile = PsiManager.getInstance(project).findFile(virtualFile!!) as RegoFile?
            if (simpleFile != null) {
                val rules = PsiTreeUtil.getChildrenOfType(simpleFile, RegoRule::class.java)
                if (rules != null) {
                    for (rule in rules) {
                        if (ruleName == rule.name) {
                            result.add(rule)
                        }
                    }
                }
            }
        }
        return result
    }

    fun findRules(project: Project?): List<RegoRule> {
        val result = mutableListOf<RegoRule>()
        val virtualFiles = FileTypeIndex.getFiles(RegoFileType, GlobalSearchScope.allScope(project!!))
        for (virtualFile in virtualFiles) {
            val regoFile = PsiManager.getInstance(project).findFile(virtualFile!!) as RegoFile?
            if (regoFile != null) {
                val rules = PsiTreeUtil.getChildrenOfType(regoFile, RegoRule::class.java)
                if (rules != null) {
                    result.addAll(rules)
                }
            }
        }
        return result
    }
}
