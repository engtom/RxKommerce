package br.com.wale.getwater.api

import br.com.wale.getwater.model.entity.*
import br.com.wale.getwater.util.Constants
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Admin on 28/07/17.
 */

interface GetWaterApiService{

    @GET("search/users")
    fun search(@Query("q") query : String,
               @Query("page") page : Int = 1,
               @Query("per_page") perPage : Int = 20) : Observable<UserResponse>

    //User

    @POST("auth")
    fun addUser(@Query("name") name: String, @Query("email") email: String, @Query("password") password: String, @Query("password_confirmation") confirmPassword: String) : Observable<Response<UserResponse>>

    @PUT("auth")
    fun editUser(params: String) : Observable<UserResponse>

    @POST("auth/sign_in")
    fun login(@Query("email") login: String, @Query("password") password: String) : Observable<Response<UserResponse>>

    //Address

    @POST("addresses")
    fun addAddress(@QueryMap address: Map<String, String>, @Header("client") client: String, @Header("access-token") accessToken: String, @Header("uid") uId: String) : Observable<Response<Address>>

    @GET("addresses")
    fun getAddress(@Query("page") page: Int = 1, @Header("client") client: String, @Header("access-token") accessToken: String, @Header("uid") uId: String) : Observable<Response<AddressResponse>>

    //Products
    @GET("stores/{id}/products")
    fun getProducts(@Path("id") storeId: Long) : Observable<Response<ProductResponse>>

    //Brands

    //Stores

    @GET("stores")
    fun getStores() : Observable<Response<StoreResponse>>

    //Orders

    @POST("orders")
    fun placeOrder(@QueryMap order: Map<String, String>, @Header("client") client: String, @Header("access-token") accessToken: String, @Header("uid") uId: String) : Observable<Response<Order>>

    companion object Factory{

        fun create(): GetWaterApiService {

            val httpClient =  OkHttpClient.Builder()
            httpClient.addInterceptor { chain: Interceptor.Chain? ->

                val original = chain?.request()

                val request = original?.newBuilder()
                        ?.header("uId", "uId")
                        ?.header("token", "token")
                        ?.header("fad", "af")
                        ?.method(original.method(), original?.body())
                        ?.build()

                chain?.proceed(request)
            }

            val client = httpClient.build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.API_URL)
                    //.client(client)
                    .build()

            return retrofit.create(GetWaterApiService::class.java)

        }

    }

}