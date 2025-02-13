package com.example.fetchapptest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapptest.domain.fetchrepository.FetchRepository
import com.example.fetchapptest.models.filterGroupSortItems
import com.example.fetchapptest.network.model.NetworkResult
import com.example.fetchapptest.presentation.model.FetchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val fetchRepository: FetchRepository
) : ViewModel() {
    private val _fetchUiScreenState = MutableStateFlow<FetchUiState>(
        FetchUiState.LoadingHolder(
            isLoading = true
        )
    )
    val fetchUiScreenState = _fetchUiScreenState.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchRepository.fetchHiringData().collectLatest { fetchItemsList ->
                when (fetchItemsList) {
                    is NetworkResult.Error -> {
                        _fetchUiScreenState.update {
                            FetchUiState.ErrorHolder(
                                msg = fetchItemsList.message ?: "",
                            )
                        }
                    }

                    is NetworkResult.Loading -> {
                        _fetchUiScreenState.update {
                            FetchUiState.LoadingHolder(
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _fetchUiScreenState.update {
                            FetchUiState.SuccessHolder(
                                list = fetchItemsList.data?.filterGroupSortItems() ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }
}