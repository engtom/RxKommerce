package br.com.wale.getwater.model.repository

import br.com.wale.getwater.api.GetWaterApiService
import br.com.wale.getwater.model.entity.User
import br.com.wale.getwater.model.entity.UserResponse
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by Admin on 29/07/17.
 */

class UserRepository(val apiService : GetWaterApiService){

    fun searchUsers(location : String, language : String) =
            apiService.search("location:$location language:$language")

    fun login(login: String, password: String) =
            apiService.login(login, password)

    fun addUser(name: String, email: String, password: String, confirmPassword: String) =
            apiService.addUser(name, email, password, confirmPassword)

    object UserRepositoryProvider{

        private  var userRepository : UserRepository? = null

        fun provideUserRepository() : UserRepository{

            if(userRepository == null){
                userRepository = UserRepository(GetWaterApiService.create())
            }

            return userRepository as UserRepository

        }

    }

}
