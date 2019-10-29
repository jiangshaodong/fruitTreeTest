package com.part.utils.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.part.entity.vo.OrderVo;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AliPayUtil {
    public static void main(String[] args) throws AlipayApiException {
        //String orderNum = OrderNumUtil.getOrderIdByUUId();
        //System.out.println(orderNum);
        //AlipayTradeAppPayResponse wwwwww = getPayResponse(orderNum, "http://47.106.145.106/pay/aliPayNotify");
        //System.out.println(wwwwww);

    }
    private static final String APP_ID_FOR_USER = "2018071860686388";
    //应用公钥
    //private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwKtuiaCwd6jBtECvHBvnZzTGlkpR8gWxH2eMMieo1LRnp3RJ7l16kO6TXJfzlrLj4a0ePXfy2gOa3QXBRDHDm4UcpAgjuonIgosWGRTgWyBFlW9TXiSWQz/aoFx17nR3zRg/rqcuNHtCpKBVVr9qMfj473iIdMajSs6Ci3cK14IyfYDE+DAObjnw09WLSEiEAhi6IIhtypY7HO8Y2sr/au1Mln1+GoLUfSzAsZzOPr8/vIWc1x7yGnRg6glbsbSStrC+YADhC9WIE2KTv17hzbKKlHiwA8F64ctgr46RzPsLX81mxMzqWEdSFWLrezlL1Hpl1jFSQr7p7GOSNtleywIDAQAB";
    //支付宝公钥
    //private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApcgm8VT+GIeNe7hyHGK2ZXVo7WvegcjHo647d80V0ZBuYyP+7kCCohMw+RhC26WX5Ewfeq6EtPCrD6dffhjetmo22/2kGCUqt3nbtEEvGhDmJDVDWo9FAQQCAoK8+ikreZcYIhy7UmsFhJJHIzTt8Sy47UTfbgJ1WdJLuw6P806IA5ZXYcF/7REyUZTmdFV/UMY1qlIouoWO9La0JNbO3U/JCMmxFiYF1jbNWSk5SiR0QLXrWjOk+kBr/B/pgRN5AC39B9cnyugZMV3pihfFdgW6wJOqA3RGlTr6eokvtUj/5NON4Nc9LzEhuak1JaM/BuaJE3PXQCg/YT2NSOxiTwIDAQAB";
    //private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDGFa1yYBG38pUxlI/BjcrJNmSOHjpYa9IFlhGGnw8MZFzenWAodFgHbOOSXbXc5+daSVVO8xoqpqO7ShRbrtRUxOY0SHxF6hGLoIhd1mZq7+TDz0pVMHElyFHd4eE/QHExL428+8ef4A5DUAtwSGQg60A5CdTP/Yrtv+l3UTSP2Yu+Aqw1PJhDCKC4K4EzuOibnie+CoK25SGCpCEa133dFNackWg02Z1D62PVTiRZlSSjETyh3Z8amT2p/Hj+d1bXP2Z7WIoM+ORLp0i93ygORhDWrKLy8XdekbQvGHU+fuPqEnXx+S75eVFGn7F11O3lxHzTFdBkjlh+g7/iUQS/AgMBAAECggEAD2icR2tltXNvW5382MeY4LIUfGGplDs+6SRVVK2G1CXye3auhjcWqe00fqw/e2Gmw4U016FdhbfUwLrwfnlJFxcB+UsyL5YXq07fjL0i1mq21uyHEb1k79V3WNU7Wlo+896JHgxP6QXa1i2h1igrccwbANTc8xj796aYGKn+droIcdoOTUjBInW61+XUYJeX+oE1Yz/l0ccYvc4Rx4RRVvCuFI2yfslRgzJj9WyZRf7o /EboBhm3B+ibUw2hg8IwktZ35PvBXBYcCQPYsIcEPfTXiFcUkDFHhE6ix6K+8jJuzaF9y53LxnrnuDGXb8NkY3fqPdu2U6Eg/p2lCDLqUQKBgQDoIKCLsP7g+t1oKUJIIq0+6wcunI6pktfUQFBh6gZ436EsR7ECx9cEIuyCkoRe1tLCwMJDGqUDP3AwS+wneoqup2LwEfSmpIJRuWTZk77On87eFvwMBpSgYtE/kupeta7z9MMEe9CXmyTyVcQIzEGBqvF3v5LQ2cU8zL8kAfkvpQKBgQDadMio74yiVMeguXXQMeNcc/hL9rxfO7Uyf8AiIvO5zdil83gYeJGytosGOqAOsg2oIGFwY3sJpYxRLQFTWo+9umeGAY/CeXZ3TEPBMsc6WWvGUuRENEeQGXjLEWhPIWYh+E74SRXXVFpJSvrPqYjE99I7Hql0RtIdnYyV9W+1kwKBgHuCuMn7QHMHKH2+zlTBlrsOpUCo7sPNrWdzXRHsSstP+lssPkRDfuXli0CioyovVe9oTzUTXU/QFmqsSxhqfJYX3G3ur+VnTSwc4Etg8LrSj2jeg/gGHFkGm14fVvNT6qZFyup80u2IEG2dMvSXcheDlH3qAj18UhVawC1xm1w9AoGBANShKRJtn3f9hxTkj0ym0kMRydK+hNk494QAnlVbqdjnCAF2+iTvbfkB/k2A0HudSPelFNJcscxQ60Ozx3+HPTORdOQVLwU+TlWN0MECmLItiV5PHklYjXMvp8gX2Gx0+MEFvG+kS3L8cqi2J2vq+umkwWaz0IIR0L/ssoLfkuyrAoGAZu7XuVezwOWjQHzlOEiGhaswLsual776JIMm/dZ/ADnMnrNFyZkqTKFYqZzYJgSaYvHMvq3ILQ1/pWEG6sinE9yK+miBjLJ0BJhziYsxfF2nkzdzumicPiKA5DsUPAzspOa1fXuU5Zj/XqBUSb3xfZZHoyXss7ZiXr65c1M1lsQ=";
    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApcgm8VT+GIeNe7hyHGK2ZXVo7WvegcjHo647d80V0ZBuYyP+7kCCohMw+RhC26WX5Ewfeq6EtPCrD6dffhjetmo22/2kGCUqt3nbtEEvGhDmJDVDWo9FAQQCAoK8+ikreZcYIhy7UmsFhJJHIzTt8Sy47UTfbgJ1WdJLuw6P806IA5ZXYcF/7REyUZTmdFV/UMY1qlIouoWO9La0JNbO3U/JCMmxFiYF1jbNWSk5SiR0QLXrWjOk+kBr/B/pgRN5AC39B9cnyugZMV3pihfFdgW6wJOqA3RGlTr6eokvtUj/5NON4Nc9LzEhuak1JaM/BuaJE3PXQCg/YT2NSOxiTwIDAQAB";
    private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDgMqquuawR2l/EyuoN9ggUBYmi5DFhQLJbJ1jvwlwJi+pTpPWqNMOd6rSIlb+UUVC/G4Xp2pZMmHR/Bj0nGjkNFprfmNglAP4xF2wjAyWFPX9cNvmSImfblSlHiW3HnBGtY/mJPbZ0ADq6XdQz7ls6pdW4/TwRWW/kY1k5L/yHovtbB/VU912c6gqLKT3RYozH6AwuYluFmPso/jZQr3I1EN1lqjewXClvI0aLIpQVjX4D1R33rrfkcRTl8bOpZm5wGBHP9erapNBFJWUOhZ1MS9BSijMtAF9haSW16nfBahuw8e39fToPHXKeCo/PHd7iMKoX7RcrLNjUOAi36GL1AgMBAAECggEAWMbXXW66WytspsFu4u5cjokO0kin4Qm8VmoDRpVg2orj1BFLEgXou/x3VmptvS5h2frtEQf0YgowfygaSQSSOMONuiKM3EJDZW8P6+FQFy2d5AcE1j/3fjTXbrNVnCgJu+4NnrGoMNDycF+9Kn8k8tk+Aw2LnpcavoY0i3eb+JzsF7qV0TEvJA6IcdJnezL7YzusRZ1+o5/0CChFlNnXzrcS1rPyw66Wd9r3tKMYl6s3Iywf9N+nMzgFHOTz72vqZFk9HT5GlWiSm067VuYdIhm/FxF/mpaZyvi7RMwb2M/Aeom6v5ACCN4nnoYl53ChbllbIk8QAUSnJFFc4VOD1QKBgQD7KZJ43QNFWROqsJppZAmHXxJ6iegyz3iOYvpXIKMQ7i/UuAWo6Q8tOuQCaaBHnjvFZwrmrSF5eQKirsB/qDG1jnVl8MrpcvKwN08wpbDYenJ66CNwpqA3PzdScpqaCNFLLwq1Gs03eQWQO4VzVPeI2YUb1GZlrLIQMhgIfCIWLwKBgQDkhCN3H8M9xLQrHQIixFmoE7h7j/X4DCa41GAM8vOpjOPrNumvbLMN4JmDXijNr8v6EjKQYTcD7s7U0Id2T9RV6mipf9xgFIPFR6QLxThIuBfiVdjq/T5n3TIHsFfhOU5kWPZkMFhgLKMmZUPAydT2ixgSsQDOUMocqVdM+/m0GwKBgGnlbUxoYyiqFhCxxWkKNLpO64ue+bfyofHm6YYyzI4m3yPhMwG0wiFf4yLhRiLsJGdmusHn3WVKEEJnS+KHF+5k0jrY+7TSMmD58Pmh4p5jgkEv/0FP/91LWhf5GhY4AYahnvIdCsmIzT+y2OF3waaBkA3b0BCub6VEeb2aGQUhAoGBAIS0hBAK3wxA7kxXRUVBd7uW8Dy5hqMtE1Ok6b67XGtrSXTwZ9cbLgRyGVRj2xhP7gnkhX65JgTfqzeFfhVkGLBoN62vdiBJ6ZgomYjIWH5KgnxLVHRq6tHQb9xtOSuvZun5i+T0+m2nnnuOtUEBNO6UKj+4kPo/ipAIe41Wi1VfAoGAFS+7g39Yrd9tTmsblEHaGIFSZ81HuxyiWfknavVe23I3PdP9C32clGoVrSZ2lQECItG690Uj6Hs2YWUklM1n4IMkSYwKmmtR+a9uB9fYNm5rw0qh1h8EZqnqQGypRhBTXJesJIfoLQlIR/Ih7lN2HEVJ9wWWFVmCNoRku1zwpvo=";
    public static AlipayTradeAppPayResponse getPayResponse(String orderNum,String orderPrice,String notifyUrl)throws AlipayApiException{
        if(StringUtils.isEmpty(notifyUrl)){
            throw new NullPointerException("请填写回调地址");
        }
        if(StringUtils.isEmpty(orderPrice)){
            throw new NullPointerException("请填写价格");
        }
        System.out.println("订单总金额为:"+orderPrice);
        AlipayClient alipayClient = null;
        //实例化客户端
            alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID_FOR_USER, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("树会长");
        model.setSubject("树会长："+ orderNum);
        model.setOutTradeNo(orderNum);//订单号
        model.setTimeoutExpress("30m");
        //测试支付0.01
        // TODO apipay 测试支付基于一分钱
//        model.setTotalAmount("0.01");
        model.setTotalAmount(orderPrice);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);//回调地址
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println("支付宝支付宝——————————————————;"+response.getBody());
        //response.getBody()
        return response;
    }
    public static AlipayTradeAppPayResponse getPayResponseForMore(List<OrderVo> orderVo, String notifyUrl, String orderNum, String ids)throws AlipayApiException{
        if(orderVo == null){
            throw new NullPointerException("找不到该订单");
        }
        if(StringUtils.isEmpty(notifyUrl)){
            throw new NullPointerException("请填写回调地址");
        }
      /*  MoreOrder mo = new MoreOrder();
        mo.setOrderIds(ids);
        mo.setOrderNum(orderNum);
        mo.setUserId(orderVo.get(0).getUserId());
        mo.setPublishTime(new Date());
        mo.insert();
        BigDecimal allPrice = new BigDecimal(0);
        for(OrderVo order:orderVo){
            Coupon coupon = order.getCoupon();//优惠券实体
            BigDecimal orderPrice = order.getOrderPrice();//订单价格
            BigDecimal distributionFee = order.getDistributionFee();//配送费价格
            if(order.getIsFreePost()==1){
                distributionFee=new BigDecimal(0);
            }
            BigDecimal realOrderPrice;
            if(coupon != null){
                realOrderPrice = orderPrice.subtract(coupon.getDiscount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                if(realOrderPrice.intValue()<0){
                    realOrderPrice=new BigDecimal(0);
                }
                realOrderPrice = realOrderPrice.add(distributionFee).setScale(2,BigDecimal.ROUND_HALF_UP);
            }else {
                realOrderPrice = orderPrice.add(distributionFee).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            allPrice = allPrice.add(realOrderPrice);
        }*/

        //System.out.println("订单总金额为"+realOrderPrice);
        AlipayClient alipayClient = null;
        //实例化客户端
        alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID_FOR_USER, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("树会长");
        model.setSubject("树会长集合支付");
        model.setOutTradeNo(orderNum);//订单号
        model.setTimeoutExpress("30m");
        //测试支付0.01
        // TODO apipay 测试支付基于一分钱
        model.setTotalAmount("0.01");
     //   model.setTotalAmount(allPrice.toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);//回调地址
        //这里和普通的接口调用不同，使用的是sdkExecute
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        System.out.println("支付宝支付宝——————————————————;"+response.getBody());
        //response.getBody()
        return response;
    }

    public static AlipayTradeRefundResponse getRefundResponse(String orderNum,String orderPrice)throws AlipayApiException{
        //realOrderPrice = new BigDecimal("0.01");
        AlipayClient alipayClient = null;
            alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID_FOR_USER,APP_PRIVATE_KEY,"json","UTF-8", ALIPAY_PUBLIC_KEY,"RSA2");

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+orderNum+"\"," +
                "\"refund_amount\":\""+ orderPrice +"\"," +
                "\"refund_reason\":\"正常退款\"" +
                "}");
        AlipayTradeRefundResponse response = alipayClient.execute(request);;
        return response;
    }

}
