package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2023-11-04 16:00
 */
@RestController
@Slf4j
public class OrderController{
    public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add",payDTO,ResultData.class);
    }
    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class, id);
    }

//
//    @DeleteMapping ("/consumer/pay/del/{id}")
//    public  ResultData deletePayOrder(@PathVariable("id") Integer id){
//        log.info("测试获取id,{}",id);
//        return restTemplate.delete(PaymentSrv_URL + "/pay/del/"+id, ResultData.class, id);
//    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData deletePayOrder(@PathVariable("id") Integer id) {
        log.info("测试获取id,{}", id);
        // 发起HTTP DELETE请求
        restTemplate.delete(PaymentSrv_URL + "/pay/del/{id}", id);
        // 返回成功的结果
        return ResultData.success("成功删除记录，ID: " + id);
    }

//    @PutMapping("/consumer/pay/update")
//    public  ResultData updatePayOrder(PayDTO payDTO){
////        return restTemplate.getForObject(PaymentSrv_URL + "/pay/update",payDTO, ResultData.class);
//        return restTemplate.postForObject(PaymentSrv_URL + "/pay/update",payDTO, ResultData.class);
//    }


    @PutMapping("/consumer/pay/update")
    public ResultData updatePayOrder(@RequestBody PayDTO payDTO) {
        // 发起HTTP PUT请求
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        // 返回成功的结果
        return ResultData.success("成功更新记录");
    }

}