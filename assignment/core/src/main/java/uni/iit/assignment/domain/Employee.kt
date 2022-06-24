package uni.iit.assignment.domain

data class Employee(
    override val id: Int,
    override var name: String,
    open var position: String,
    override var location: String,
    open var salary: Int,
): Person(id, name, location)