package com.devkishan.nseapi.repo

import com.devkishan.nseapi.api.NseApi
import com.devkishan.nseapi.model.AllIndices
import javax.inject.Inject

class NseRepository @Inject constructor(private val nseApi: NseApi) {
//    suspend fun getPhotos() {
//       Thread {
//          try { val call: Call<List<Photo>> = myApi.getPhotos()
//           val response = call.execute()
//           Log.d("Networking", "Success: ${response.isSuccessful}")
//           Log.d("Networking", "NetworkingRepository: ${response.body()}")
//
//       } catch (e:Exception){
//       }
//
//        }.start()
//
//
//    }

//    fun getPhotos(onSuccess:(List<Photo>)->Unit){
//
//    try {
//
//        val call:Call<List<Photo>> =  myApi.getPhotos()
//        val response = call.enqueue(object:Callback<List<Photo>>{
//            override fun onResponse(call: Call<List<Photo>>, response: retrofit2.Response<List<Photo>>) {
//                val responseBody=response.body()
//                Log.d("Networking", "Success: ${response.isSuccessful}")
//                Log.d("Networking", "NetworkingRepository: $responseBody")
//                onSuccess(responseBody!!)
//        }
//
//            override fun onFailure(p0: Call<List<Photo>>, p1: Throwable) {
//                Log.d("Networking", "Failure: ${p1.message}")
//            }
//
//        })
//
//    } catch (e:Exception){
//        Log.d("Networking", "exception: ${e.message}")
//    }
//
//    }

   suspend fun getAllIndices():List<AllIndices>{

      return nseApi.getAllIndices()
    }
    suspend fun deleteIndex(indexSymbol:String){
        return nseApi.deleteIndex(indexSymbol)
    }

/*    suspend fun createPhoto(photo: Photo):Photo{
return myApi.createPhoto(photo)

    }
    suspend fun deletePhoto(id:Int){
        return myApi.deletePhoto(id)
    }*/
}