package com.example.questapi_222.viewmodel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_222.modeldata.DataSiswa
import com.example.questapi_222.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<DataSiswa> = listOf()) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModel(private val repositoryDataSiswa: RepositoryDataSiswa) :
    ViewModel() {
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadSiswa() {
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getDataSiswa())
            } catch (e: IOException) {
                StatusUiSiswa.Error
            } catch (e: HttpException) {
                StatusUiSiswa.Error
            }
        }
    }
}