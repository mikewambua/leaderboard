package com.micaiah.leaderboard.apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiManager {
    @FormUrlEncoded
    @POST ("formResponse/")
    Call<ResponseBody> formResponse(
            @Field("entry.1877115667")String firstName,
            @Field("entry.2006916086")String lastName,
            @Field("entry.1824927963")String emailAddress,
            @Field("entry.284483984")String linkToProject

    );

}
