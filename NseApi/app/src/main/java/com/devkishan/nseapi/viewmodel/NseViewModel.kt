package com.devkishan.nseapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devkishan.nseapi.model.AllIndices
import com.devkishan.nseapi.repo.NseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NseViewModel @Inject constructor(
    private val nseRepository: NseRepository
) : ViewModel() {

    //    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
//    val photos:StateFlow<List<Photo>> get() =_photos
    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    sealed interface UiState {
        data object Empty : UiState
        data object Loading : UiState
        data class Success(val indices: List<AllIndices>) : UiState
        data class Error(val message: String) : UiState
    }

    init {
        getAllIndices()
    }

     fun getAllIndices() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val indices = nseRepository.getAllIndices()
                _uiState.value = UiState.Success(indices)
            } catch (e: Exception) {
                val errMessage = e.message ?: "Unknown Error"
                _uiState.value = UiState.Error(errMessage)
                Log.d("Networking", "exception in vm: ${e.message}")
            }

        }

    }

    fun deleteIndex(indexSymbol: String) {
        viewModelScope.launch {
            try {
                nseRepository.deleteIndex(indexSymbol)
                Log.d("Networking", "deletedIndex: $indexSymbol")
            } catch (e: Exception) {
                Log.d("Networking", "exception in vm: ${e.message}")
            }
        }
    }
}


//    fun getPhotos() {
//        viewModelScope.launch {
////            networkingRepository.getPhotos(onSuccess = {photos->
////                _photos.value = photos
////            })
////           // _photos.value = photos
//
////            try {
////                val photos= networkingRepository.getPhotos()
////                _photos.value = photos
////            }
////            catch (e:Exception){
////                Log.d("Networking", "exception in vm: ${e.message}")
////            }
//            _uiState.value = UiState.Loading
//            try {
//                val photos = networkingRepository.getPhotos()
//                _uiState.value = UiState.Success(photos)
//            } catch (e: Exception) {
//                val errMessage = e.message ?: "Unknown Error"
//                _uiState.value = UiState.Error(errMessage)
//                Log.d("Networking", "exception in vm: ${e.message}")
//            }
//
//        }
//
//    }
//
//
//    fun createPhoto(photo: Photo) {
//        viewModelScope.launch {
//            try {
//                val createdPhoto = networkingRepository.createPhoto(photo)
//                Log.d("Networking", "createdPhoto: $createdPhoto")
//            } catch (e: Exception) {
//                Log.d("Networking", "exception in vm: ${e.message}")
//            }
//
//        }
//    }
//
//    fun deletePhoto(id: Int) {
//        viewModelScope.launch {
//            try {
//                networkingRepository.deletePhoto(id)
//                Log.d("Networking", "deletedPhoto: $id")
//            } catch (e: Exception) {
//                Log.d("Networking", "exception in vm: ${e.message}")
//            }
//        }
//
//
//    }
