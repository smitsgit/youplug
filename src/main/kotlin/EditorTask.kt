import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ScriptRunnerUtil

import java.nio.charset.Charset

class EditorIllustrationAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) { // Get all the required data from data keys
        val editor = e.getRequiredData(
            CommonDataKeys.EDITOR
        )
        val project = e.getRequiredData(
            CommonDataKeys.PROJECT
        )
        val document = editor.document
        // Work off of the primary caret to get the selection info
        val primaryCaret = editor.caretModel.primaryCaret
        val start = primaryCaret.selectionStart
        val end = primaryCaret.selectionEnd

        // Replace the selection with a fixed string.
// Must do this document change in a write action context.
        WriteCommandAction.runWriteCommandAction(
            project
        ) {
            document.replaceString(start, end, "dummy text") }
        // De-select the text range that was just replaced
        primaryCaret.removeSelection()
    }

    override fun update(e: AnActionEvent) { // Get required data keys
        val project = e.project
        val editor = e.getData(
            CommonDataKeys.EDITOR
        )
        // Set visibility only in case of existing project and editor and if a selection exists
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }
}