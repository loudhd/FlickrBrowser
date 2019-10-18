package com.example.flickrbrowser

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class GetFlickrJsonData(private val listener:onDataAvvailable):AsyncTask<String,Void,ArrayList<Photo>>() {
    private val TAG = "GetFlickrJsonData"

    interface onDataAvvailable {
        fun onDataAvailable(data: List<Photo>)
        fun onError(exception: Exception)
    }

    override fun onPostExecute(result: Array<Photo>?) {
        Log.d(TAG, "onPostExecute starts")
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Log.d(TAG, "doInBackground starts")

        val photoList = ArrayList<Photo>()
        try {
            val jsonData = JSONObject(params[0])
            val itemArray = jsonData.getJSONArray("item")

            for (i in 0 until itemArray.length()) {
                val JsonPhoto = itemArray.getJSONObject(i)
                val title = JsonPhoto.getString("title")
                val author = JsonPhoto.getString("author")
                val authorId = JsonPhoto.getString("author_id")
                val tags = JsonPhoto.getString("tags")

                val jsonMedia = JsonPhoto.getString("media")
                val photoUrl = JsonPhoto.getString("m")
                val link = photoUrl.replaceFirst("_m.jpg", "_b.jpg")

                val photoObject = Photo(title, author, authorId, link, tags, photoUrl)

                photoList.add(photoObject)
                Log.d(TAG, ".doInBackground $photoObject")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e(TAG, ".doInBackground: Error processing Json data. ${e.message}")
            cancel(true)
            listener.onError(e)
        }
        Log.d(TAG, ".doInBackground ends")
        return photoList
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(TAG, "onPostExecute starts")
        super.onPostExecute(result)
        listener.onDataAvailable(result)
        Log.d(TAG, ".onPostExecute ends")

    }
}