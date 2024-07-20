package model

enum class Priority {
    Low, Medium, High, Vital
}

data class Task(
    val name: String,
    val description: String,
    val priority: Priority
)

fun Task.taskAsRow() = """
    <tr>
        <td>$name</td><td>$description</td><td>$priority</td>
    </tr>
    """.trimIndent()

fun List<Task>.tasksAsTable() = this.joinToString(
    prefix = "<table rules=\"all\">",
    postfix = "</table>",
    separator = "\n",
    transform = Task::taskAsRow
)

object TaskRepository{
    val tasks = mutableListOf(
        Task("clearning", "Clean the house", Priority.Low),
        Task("gardening", "Mow the Law", Priority.Medium),
        Task("shopping", "Buy the groceries", Priority.High),
        Task("painting", "Paint the fence", Priority.Medium)
    )
    fun allTasks(): List<Task> = tasks

    fun tasksByPriority(priority: Priority) = tasks.filter{
        it.priority == priority
    }

    fun taskByName(name: String) = tasks.find{
        it.name.equals(name, ignoreCase = true)
    }
    fun addTaks(task: Task) {
        if (taskByName(task.name) != null ){
            throw IllegalStateException("Cannot duplicate task names!")
            }
        tasks.add(task)
    }

}
