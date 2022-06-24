package uni.iit.assignment.fragment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uni.iit.assignment.domain.Summary
import uni.iit.assignment.interactors.GetSummary

class HomeViewModel(private val getSummary: GetSummary) : ViewModel() {

    private val _summary = MutableLiveData<Summary>().apply {
        value = Summary(0f, 0f, 0f, 0f, 0,0,0, 0)
    }
    val summary: LiveData<Summary> = _summary

    fun loadSummary() {
        viewModelScope.launch {
            _summary.postValue(getSummary())
        }
    }
}