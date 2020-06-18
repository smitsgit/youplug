import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class MyAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val notification = NotificationGroup("greatplug", NotificationDisplayType.BALLOON, true)
        notification.createNotification("Hello My title", "My Message", NotificationType.INFORMATION, null)
            .notify(e.project)
    }
}