package uni.iit.assignment.data

class SummaryRepository(private val dataSource: SummaryDataSource) {
    suspend fun fetchSummary() = dataSource.fetchSummary()
}