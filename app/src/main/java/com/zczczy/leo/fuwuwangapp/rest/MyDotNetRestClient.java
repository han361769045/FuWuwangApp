package com.zczczy.leo.fuwuwangapp.rest;

import com.zczczy.leo.fuwuwangapp.model.BaseModel;
import com.zczczy.leo.fuwuwangapp.model.BaseModelJson;
import com.zczczy.leo.fuwuwangapp.model.Lottery;
import com.zczczy.leo.fuwuwangapp.model.LotteryConfig;
import com.zczczy.leo.fuwuwangapp.model.LotteryInfo;
import com.zczczy.leo.fuwuwangapp.model.NewBanner;
import com.zczczy.leo.fuwuwangapp.model.PagerResult;
import com.zczczy.leo.fuwuwangapp.model.RebuiltCartInfo;
import com.zczczy.leo.fuwuwangapp.model.RebuiltRecommendedGoods;


import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.androidannotations.rest.spring.api.RestClientRootUrl;
import org.androidannotations.rest.spring.api.RestClientSupport;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2016/3/2.
 * http://124.254.56.58:8007/
 * http://appapia.86fuwuwang.com/
 */
@Rest(rootUrl = "http://192.168.0.198:8002/", requestFactory = MyOkHttpClientHttpRequestFactory.class, interceptors = {MyInterceptor.class},
        converters = {StringHttpMessageConverter.class, MappingJackson2HttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter.class})
public interface MyDotNetRestClient extends RestClientRootUrl, RestClientSupport, RestClientHeaders, RestClientErrorHandling {


    /**
     * 功能：订阅安全信使
     * <p/>
     *
     * @param map SendCode  验证码
     *            UserName  用户名
     *            mobile 电话
     *            TwoPW	支付密码
     * @return
     */
    @Post("api/SMS/SubscriptionService")
    BaseModelJson<String> SubscriptionService(@Body Map map);

    /**
     * 功能：取消订阅安全信使
     * <p/>
     *
     * @param map SendCode  验证码
     *            UserName  用户名
     *            mobile 电话
     * @return
     */
    @Post("api/SMS/CancelSubscription")
    BaseModelJson<String> CancelSubscription(@Body Map map);

    /**
     * 功能：获取手机验证码
     * <p/>
     *
     * @param map SendType （0：提现，1：变更资料，2：订阅服务，3：取消订阅,4.转账）
     *            UserName  用户名
     *            mobile 电话
     * @return String
     */
    @Post("api/SMS/SendVerificationCode")
    BaseModelJson<String> SendVerificationCode(@Body  Map map);

    /**
     * 功能：验证验证码
     * <p/>
     *
     * @param username 用户名
     * @param code     验证码
     * @param SendType 类型 （0：提现，1：变更资料，2：订阅服务，3：取消订阅,4.转账）
     * @return String
     */
    @Get("api/SMS/VerifyExite?username={username}&code={code}&SendType={SendType}")
    BaseModelJson<String> VerifyExite(@Path String username, @Path String code, @Path String SendType);

    /**
     * SubscriptionExist
     * 功能：根据用户名验证查询是否订阅
     * <p/>
     *
     * @param UserName 用户名
     * @return String
     */

    @Get("api/SMS/SubscriptionExist?UserName={UserName}")
    BaseModelJson<String> SubscriptionExist(@Path String UserName);

    /**
     * SubscriptionExist
     * 功能：获取电话
     *
     * @param UserName 用户名
     * @return 获取电话
     */

    @Get("api/SMS/GetMobile?UserName={UserName}")
    BaseModelJson<String> GetMobile(@Path String UserName);

    /**
     * 查询：是否取消订阅
     *
     * @param UserName 登录帐号
     * @return BaseModel
     */
    @Get("api/SMS/checkIsExistSubByUserName?UserName={UserName}")
    BaseModel checkIsExistSubByUserName(@Path String UserName);

    /**
     * 查询抽奖配置信息
     *
     * @return LotteryConfig
     * @see LotteryConfig
     */
    @Get("api/Lottery/GetLotteryConfigInfo")
    BaseModelJson<LotteryConfig> getLotteryConfigInfo();

    /**
     * 查询所有中奖信息
     *
     * @param PageIndex 页号
     * @param PageSize  每页显示多少
     * @return PagerResult<LotteryInfo>
     * @see PagerResult
     * @see LotteryInfo
     */
    @Get("api/Lottery/GetAllLotteryInfo?PageIndex={PageIndex}&PageSize={PageSize}")
    BaseModelJson<PagerResult<LotteryInfo>> getAllLotteryInfo(@Path int PageIndex,@Path  int PageSize);

    /**
     * 排队抽奖处理
     *
     * @param UserName 登录帐号
     * @return Lottery
     * @see Lottery
     */
    @Get("api/Lottery/Lottery?UserName={UserName}")
    BaseModelJson<Lottery> lottery(@Path String UserName);


    /**
     * 查询会员自己的中奖信息
     *
     * @param UserName  登录帐号
     * @param PageIndex 页号
     * @param PageSize  每页显示多少
     * @return PagerResult<LotteryInfo>
     * @see PagerResult
     * @see LotteryInfo
     */
    @Get("api/Lottery/GetMyLotteryInfo?UserName={UserName}&PageIndex={PageIndex}&PageSize={PageSize}")
    BaseModelJson<PagerResult<LotteryInfo>> getMyLotteryInfo(@Path String UserName,@Path  int PageIndex,@Path  int PageSize);

    /**
     * 获取龙币
     * Header  Token
     *
     * @return Double
     */
    @Get("api/Member/GetMemberElectronicMoney")
    @RequiresHeader(value = "Token")
    BaseModelJson<Double> GetMemberElectronicMoney();


    /**
     * 获取电子币
     *
     * @return Integer
     */
    @Get("api/Member/GetMemberLongBi")
    @RequiresHeader(value = "Token")
    BaseModelJson<Integer> GetMemberLongBi();

    /**
     * 获取新服务网Banner
     */
    @Get("api/ShopContent/GetHomeBanner")
    BaseModelJson<List<NewBanner>> GetHomeBanner();

    /**
     * 根据广告区分查询广告信息（1：首页广告，2：服务类页面广告）
     *
     * @param kbn
     * @return
     */
    @Get("api/ShopContent/GetAdvertByKbn?kbn={kbn}")
    BaseModelJson<PagerResult<RebuiltRecommendedGoods>> getAdvertByKbn(@Path String kbn);

    /**
     * 查询推荐商品
     *
     * @param PageIndex
     * @param PageSize
     * @return
     */
    @Get("api/ShopContent/GetRecommendedGoods?PageIndex={PageIndex}&PageSize={PageSize}")
    BaseModelJson<PagerResult<RebuiltRecommendedGoods>> getRecommendedGoods(@Path  int PageIndex,@Path  int PageSize);


    /**
     *查询用户购物车信息
     * @return
     */
    @Get("api/Shop/GetBuyCartInfo")
    @RequiresHeader(value = "Token")
    BaseModelJson<List<RebuiltCartInfo>> getBuyCartInfo();


}
