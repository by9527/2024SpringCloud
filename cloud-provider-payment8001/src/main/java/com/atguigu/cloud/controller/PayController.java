package com.atguigu.cloud.controller;


import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")

public class PayController {

    @Resource
    private PayService payService;


    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());

        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值: " + i) ;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable Integer id){
        int i = payService.delete(id);
        return  ResultData.success(i);
    }

    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);

        int i = payService.update(pay);

        return ResultData.success("成功修改记录，返回值: " + i) ;
    }



    @GetMapping("pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable Integer id){
        if(id < 0 || id == 0) throw new RuntimeException("id不能为0或负数");
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }


    @GetMapping("/pay/getAll")
    @Operation(summary = "查询全部",description = "查询全部支付流水方法")
    public ResultData<List<Pay>> getAll(){
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }


    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayError()
    {
        Integer integer = Integer.valueOf(200);
        try
        {
            System.out.println("come in payerror test");
            int age = 10/0;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(integer);
    }

}
