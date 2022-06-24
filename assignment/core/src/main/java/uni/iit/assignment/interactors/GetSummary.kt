package uni.iit.assignment.interactors

import uni.iit.assignment.data.SummaryRepository

class GetSummary (private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke() = summaryRepository.fetchSummary()
}