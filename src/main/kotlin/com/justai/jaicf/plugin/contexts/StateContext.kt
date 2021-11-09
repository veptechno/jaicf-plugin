package com.justai.jaicf.plugin.contexts

import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiFile
import com.justai.jaicf.plugin.getBoundedCallExpressionOrNull
import com.justai.jaicf.plugin.getBoundedLambdaArgumentOrNull
import com.justai.jaicf.plugin.scenarios.psi.builders.isStateDeclaration
import org.jetbrains.kotlin.psi.KtCallExpression

class StateContext : TemplateContextType("STATE", "State") {
    override fun isInContext(file: PsiFile, offset: Int): Boolean {
        val probeElement = file.findElementAt(offset)
        val lambdaArgument = probeElement?.getBoundedLambdaArgumentOrNull(KtCallExpression::class.java) ?: return false
        return lambdaArgument.getBoundedCallExpressionOrNull()?.isStateDeclaration == true
    }
}
