package com.smart.brd.manage.base.common.test;

import com.smart.brd.manage.base.entity.TLivestockAnalysis;
import com.smart.brd.manage.base.service.ITAlarmInfoService;
import com.smart.brd.manage.base.service.ITDiseaseTreatService;
import com.smart.brd.manage.base.service.ITLivestockService;
import com.smart.common.core.domain.Result;
import com.smart.common.core.domain.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dukzzz
 * @date 2021/3/19 16:46:下午
 * @desc
 */
@RestController
@Api(tags = "统计数量")
@RequestMapping("/api/v1/brd/talarminfo")
public class GetNum {

    @Resource
    private ITAlarmInfoService tAlarmInfoService;
    @Autowired
    private ITDiseaseTreatService tDiseaseTreatService;
    @Resource
    private ITLivestockService livestockService;

    /**
     * 测试数字常量
     *
     */
    private static final Integer TEST_NUM = 11;

    @ApiOperation(value = "告警数量")
    @GetMapping("/getUnprocessedNum")
    public Result getUnprocessedNum(TLivestockAnalysis tLivestockAnalysis) {
        try {
            int unprocessedNum= tAlarmInfoService.getUnprocessedNum(tLivestockAnalysis);
            return Result.SUCCESS(unprocessedNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }
    @ApiOperation(value = "未康复数量")
    @GetMapping("/getTreatNum")
    public Result getTreatNum(TLivestockAnalysis tLivestockAnalysis) {
        try {
            int treatNum= tDiseaseTreatService.getTreatNum(tLivestockAnalysis);
            return Result.SUCCESS(treatNum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }


    @GetMapping("/livestocktest")
    public Result livestocktest() {
        try {
            livestockService.preTransferList();
            livestockService.suitableListSet();
            return Result.SUCCESS("--");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.ERROR(ResultCode.FAIL);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        for(int i = TEST_NUM; i >= 0; i--){
            LocalDate month = now.minusMonths(i);
            String m = df.format(month);
            list.add(m);
        }
        list.add("1");
    }

}
