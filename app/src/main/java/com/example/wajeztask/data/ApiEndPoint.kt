package com.example.wajeztask.data

import com.example.wajeztask.data.dto.WizardsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface APIEndpoints {

    @FormUrlEncoded
    @POST("/Wizards")
    suspend fun getWizardsList(
        @Field("FirstName") firstName: String,
        @Field("LastName") lastName: String
    ): List<WizardsResponse>


}