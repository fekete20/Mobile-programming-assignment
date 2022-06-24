package uni.iit.assignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uni.iit.assignment.db.dao.CustomerDao
import uni.iit.assignment.db.dao.EmployeeDao
import uni.iit.assignment.db.entity.CustomerEntity
import uni.iit.assignment.db.entity.EmployeeEntity

    @Database(
        entities = [EmployeeEntity::class, CustomerEntity::class],
        version = 1,
        exportSchema = false
    )

    abstract class AssignmentDatabase : RoomDatabase() {
        companion object {
            private const val DATABASE_NAME = "employee.db"
            private var instance: AssignmentDatabase? = null

            private val DB_CALLBACK = object: RoomDatabase.Callback() {

            }

            private fun create(context: Context) : AssignmentDatabase = Room.databaseBuilder(context, AssignmentDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().addCallback(DB_CALLBACK).build()

            fun getInstance(context: Context) : AssignmentDatabase = (instance ?: create(context).also { instance = it })
        }
        abstract fun employeeDao() : EmployeeDao
        abstract fun customerDao() : CustomerDao
    }


