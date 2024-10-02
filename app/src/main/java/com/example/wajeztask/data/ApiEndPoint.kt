package com.example.wajeztask.data

import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.data.dto.WizardsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIEndpoints {

    @GET("/Wizards")
    suspend fun getWizardsList(
        @Query("FirstName") firstName: String,
        @Query("LastName") lastName: String
    ): List<WizardsResponse>


    @GET("/Wizards/{id}")
    suspend fun getWizardsDetails(
        @Path("id") id: String,
    ): WizardsResponse

    @GET("/Elixirs/{id}")
    suspend fun getElixirsDetails(
        @Path("id") id: String,
    ): ElixirsResponse


}