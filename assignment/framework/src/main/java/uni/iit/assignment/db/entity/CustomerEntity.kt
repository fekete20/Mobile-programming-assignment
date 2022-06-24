package uni.iit.assignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")

data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val name: String,
    val location: String,
    val comment: String
)