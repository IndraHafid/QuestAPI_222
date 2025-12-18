package com.example.questapi_222.viewmodel

import com.example.questapi_222.modeldata.DataSiswa

sealed class StateUiSiswa {

    object Loading : StateUiSiswa()

    data class Success(
        val data: List<DataSiswa>
    ) : StateUiSiswa()

    object Error : StateUiSiswa()
}
