package nugraha.angga.com.learnmvvmwitharch.api

import io.reactivex.Observable
import nugraha.angga.com.learnmvvmwitharch.api.dao.MainDataDao
import nugraha.angga.com.learnmvvmwitharch.api.dao.RepoDataDao
import nugraha.angga.com.learnmvvmwitharch.util.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {
    @GET("users/{username}")
    fun getMainData(
        @Path("username") username:String
    ):Observable<MainDataDao>

    @GET("https://api.github.com/users/{username}/repos")
    fun getReposData(
        @Path("username") username:String
    ):Observable<List<RepoDataDao>>

    companion object {
        fun create():ApiServices{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }

}