/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.openpolicyagent.ideaplugin.lang.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import org.openpolicyagent.ideaplugin.lang.RegoFileType


object RegoElementFactory {
    fun createRule(project: Project?, name: String): RegoRule {
        val file = createFile(project, name)
        return file.firstChild as RegoRule
    }

    fun createFile(project: Project?, text: String): RegoFile {
        val name = "dummy.rego"
        return PsiFileFactory.getInstance(project).createFileFromText(name, RegoFileType, text) as RegoFile
    }
}