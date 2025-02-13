package com.example.fetchapptest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapptest.domain.fetchrepository.FetchRepository
import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.models.FetchItemsList
import com.example.fetchapptest.models.filterGroupSortItems
import com.example.fetchapptest.network.model.NetworkResult
import com.example.fetchapptest.presentation.model.FetchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val fetchRepository: FetchRepository
) : ViewModel() {
    private val _fetchUiScreenState = MutableStateFlow<FetchUIState>(
        FetchUIState.Loading(list = emptyList(), isPaginating = false)
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
                            FetchUIState.Error(
                                list = emptyList(),
                                msg = fetchItemsList.message ?: "",
                                isPaginating = false
                            )
                        }
                    }

                    is NetworkResult.Loading -> {
                        _fetchUiScreenState.update {
                            FetchUIState.Loading(
                                list = emptyList(),
                                isPaginating = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _fetchUiScreenState.update {
                            FetchUIState.Data(
                                list = fetchItemsList.data?.filterGroupSortItems() ?: emptyList(),
                                canPaginate = true
                            )
                        }
                    }
                }

            }
        }
    }

    private fun buildSkeletonList(): FetchItemsList {
        return FetchItemsList(
            fetchItems = List(10) { FetchItem(Random.nextInt(), Random.nextInt(), "") },
            isSkeleton = true
        )
    }
}