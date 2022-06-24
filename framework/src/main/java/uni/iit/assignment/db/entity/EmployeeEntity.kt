package uni.iit.assignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")

data class EmployeeEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        val name: String,
        val position: String,
        val location: String,
        val salary: Int
    )
