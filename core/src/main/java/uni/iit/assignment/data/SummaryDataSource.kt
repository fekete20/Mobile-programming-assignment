package uni.iit.assignment.data

import uni.iit.assignment.domain.Summary

interface SummaryDataSource {
    suspend fun fetchSummary() : Summary
}