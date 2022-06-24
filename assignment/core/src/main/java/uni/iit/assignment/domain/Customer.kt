package uni.iit.assignment.domain

data class Customer(
    override val id: Int,
    override var name: String,
    override var location: String,
    open var comment: String
): Person(id, name, location)