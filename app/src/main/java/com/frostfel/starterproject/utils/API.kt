package com.frostfel.starterproject.utils

/**
 * Created by Alvaro on 08/06/2018.
 */
import android.app.Activity
import android.util.Log
import android.view.View
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.fuel.core.interceptors.cUrlLoggingRequestInterceptor
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import java.nio.charset.Charset

/**
 * Created by alvaro on 23/4/18.
 */

typealias ParamItem = Pair<String, Any?>

class API {


    companion object {
        val timeout = 120000
        val apiURL = "apiurl"
        val mapper = Gson()
        val BUILD_DEBUG = true

        fun initManager() {
            FuelManager.instance.basePath = apiURL
            FuelManager.instance.baseHeaders = mapOf("Content-type" to "application/json")
            if (BUILD_DEBUG) {
                logAPI()
            }
        }

        private fun logAPI() {
            val LOG_DEBUG_KEY = "Frostfel"
            FuelManager.instance.addRequestInterceptor(cUrlLoggingRequestInterceptor())
            FuelManager.instance.addResponseInterceptor {
                next: (Request, Response) -> Response ->
                { req: Request, res: Response ->
                    Log.d(LOG_DEBUG_KEY, "REQUEST COMPLETED: " +req.toString())
                    Log.d(LOG_DEBUG_KEY, "RESPONSE: " +res.toString())
                    next(req, res)
                }
            }
        }

        private fun setupAuthHeader() {

        }

        fun request(endPoint: String, onSuccess: (resultResponse: Result<String, FuelError>) -> Unit = { resultResponse -> {}}
                    , onFailure: (resultResponse: Result<String, FuelError>, context: Activity, retryAction: () -> Unit?, statusCode: Int) -> Unit = { resultResponse, context, retryAction, statusCode -> manageError(resultResponse, context, retryAction, statusCode) }
                    , params: List<Pair<String, Any?>>? = null
                    , body: Any? = null
                    , showDialog: Boolean = false
                    , context: Activity
                    , method: Method) {

            setupAuthHeader()
            val request = when (method) {
                Method.GET -> endPoint.httpGet(params).timeout(timeout).timeoutRead(timeout)
                Method.POST -> endPoint.httpPost(params).timeout(timeout).timeoutRead(timeout)
                Method.PUT -> endPoint.httpPut(params).timeout(timeout).timeoutRead(timeout)
                Method.DELETE -> endPoint.httpDelete(params).timeout(timeout).timeoutRead(timeout)
                Method.PATCH -> endPoint.httpPatch(params).timeout(timeout).timeoutRead(timeout)
                else -> null
            }

            if(request != null){
                request.body(mapper.toJson(body)).responseString { _, response, result ->
                    when (result) {

                        is Result.Failure -> {
                            onFailure(result, context, { request(endPoint, onSuccess, onFailure, params, body, showDialog, context, method) }, response.statusCode)
                        }
                        is Result.Success -> {
                            manageSuccess(onSuccess, result,context)
                        }
                    }
                }
            }


        }



        private fun manageSuccess(onSuccess: (resultResponse: Result<String, FuelError>) -> Unit, result: Result<String, FuelError>, context: Activity) {
            onSuccess(result)
        }


        private fun manageError(result: Result<String, FuelError>, context: Activity, retryAction: () -> Unit?, statusCode: Int) {


        }




    }
}