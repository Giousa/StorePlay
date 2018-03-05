package com.zmm.storeplay.common.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zmm.storeplay.common.Constant;
import com.zmm.storeplay.common.util.DensityUtil;
import com.zmm.storeplay.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/3/2
 * Time:上午12:07
 */

public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Context mContext;

    public CommonParamsInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();


        try {

            String method = request.method();

            //TODO 继续
            HashMap<String,Object> commonParamsMap = new HashMap<>();
            //需要权限
//            commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commonParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
            commonParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
            commonParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
            commonParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
            commonParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK()+"");
            commonParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");



            if(method.equals("GET")){

                HttpUrl httpUrl = request.url();

                HashMap<String,Object> rootMap = new HashMap<>();

                Set<String> parameterNames = httpUrl.queryParameterNames();
                for (String key:parameterNames) {
                    if(Constant.PARAM.equals(key)){
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        if(oldParamJson != null){
                            HashMap<String, Object> p = (HashMap<String, Object>) JSONObject.parseObject(oldParamJson, new TypeReference<Map<String, Object>>(){});
                            if(p != null){

                                for (Map.Entry<String,Object> entry:p.entrySet()) {
                                    rootMap.put(entry.getKey(),entry.getValue());
                                }

                            }
                        }else {
                            rootMap.put(key,httpUrl.queryParameter(key));
                        }
                    }
                }

                rootMap.put("publicParams",commonParamsMap);

//                String newJsonParams = (String) JSONObject.toJSON(rootMap);
                String newJsonParams = com.alibaba.fastjson.JSON.toJSONString(rootMap);
                System.out.println("GET::newJsonParams = "+newJsonParams);

                String url = httpUrl.toString();

                int index = url.indexOf("?");
                if(index > 0){
                    url = url.substring(0,index);
                }

                url = url+"?"+Constant.PARAM+"="+newJsonParams;

                System.out.println("GET::url = "+url);

                request = request.newBuilder().url(url).build();

            }else if(method.equals("POST")) {

                RequestBody body = request.body();


                HashMap<String, Object> rootMap = new HashMap<>();
                if (body instanceof FormBody) { // form 表单

                    for (int i = 0; i < ((FormBody) body).size(); i++) {

                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }

                } else {

                    Buffer buffer = new Buffer();

                    body.writeTo(buffer);

                    String oldJsonParams = buffer.readUtf8();

//                    rootMap = mGson.fromJson(oldJsonParams,HashMap.class); // 原始参数
                    rootMap = (HashMap<String, Object>) JSONObject.parseObject(oldJsonParams, new TypeReference<Map<String, Object>>() {});
                    rootMap.put("publicParams", commonParamsMap); // 重新组装
                    String newJsonParams = com.alibaba.fastjson.JSON.toJSONString(rootMap);


                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();


                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }




        return chain.proceed(request);
    }
}
