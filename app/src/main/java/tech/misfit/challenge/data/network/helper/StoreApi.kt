package tech.misfit.challenge.data.network.helper

import retrofit2.http.GET
import tech.misfit.challenge.data.model.ProductInfo
import tech.misfit.challenge.data.model.StoreInfo

interface StoreApi {
    @GET("storeInfo")
    suspend fun getStoreInfo(): StoreInfo?

    @GET("products")
    suspend fun getProductList(): List<ProductInfo>?
//
//    @GET("/HRMS/EmployeeAttendanceM/GetMLeaveTypesBalance")
//    suspend fun leaveBalances(): RetrofitResponse<List<LeaveBalance>>
//
//    @GET("/HRMS/EmployeeAttendanceM/GetMTimeChangeReasonList")
//    suspend fun timeChangeReasons(): RetrofitResponse<List<TimeChangeReason>>
//
//    @POST("HRMS/EmployeeAttendanceM/SaveMLeaveApplication")
//    suspend fun saveLeaveReq(
//        @Body leaveReq: LeaveReq
//    ): SaveResponse
//
//    @POST("HRMS/EmployeeAttendanceM/SaveMLateApprovalApplication")
//    suspend fun saveOutsideDutyReq(
//        @Body outsideDutyReq: OutsideDutyReq
//    ): SaveResponse
//
//    @POST("HRMS/EmployeeAttendanceM/SaveMLateApprovalApplication")
//    suspend fun saveLateReq(
//        @Body lateReq: LateReq
//    ): SaveResponse
//
//    @POST("HRMS/EmployeeAttendanceM/SaveMEarlyOutApplication")
//    suspend fun saveEarlyOutReq(
//        @Body earlyOutReq: EarlyOutReq
//    ): SaveResponse
//
//    @GET("/HRMS/EmployeeAttendanceM/GetMApplicationInfo")
//    suspend fun getApplicationInfo(
//        @Query("docCode") docCode: String,
//        @Query("applicationCode") applicationCode: String,
//        @Query("year") year: String
//    ): RetrofitResponse<ApplicationInfo>
//
//    @GET("/HRMS/EmployeeAttendanceM/GetMApplicationInfo")
//    suspend fun getApplicationInfoJ(
//        @Query("docCode") docCode: String,
//        @Query("applicationCode") applicationCode: String,
//        @Query("year") year: String
//    ): JsonObject
}