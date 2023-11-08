package com.student.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = findViewById<TextView>(R.id.title)
        val retrofit = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)
//        CoroutineScope(Dispatchers.IO).launch {
//            val pathResponse : LiveData<Response<List<AlbumsItem>>> = liveData {
//                val response = retrofit.getSortedAlbums2(3)
//                emit(response)
//            }
//            pathResponse.observe(this@MainActivity) {
//                val title:String = it.body()?.get(0)?.title.toString()
//                Toast.makeText(this@MainActivity, "Path : $title", Toast.LENGTH_SHORT).show()
//            }
//        }



         CoroutineScope(Dispatchers.Main).launch {
                val responseLiveData: LiveData<Response<List<AlbumsItem>>> = liveData {
                    val response = retrofit.uploadAlbum(AlbumsItem(0, "New Album", 3))
                    emit(response)
                }
             responseLiveData.observe(this@MainActivity) { response ->
                    val albumsList = response.body()?.listIterator()
                    if(albumsList != null){
                        while (albumsList.hasNext()){
                            val albumsItem = albumsList.next()
                            val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                                    " " + "Album id : ${albumsItem.id}" + "\n" +
                                    " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                            title.append(result)
                        }
                    }
             }
         }
    }
}