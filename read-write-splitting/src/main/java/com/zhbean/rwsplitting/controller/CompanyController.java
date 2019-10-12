package com.zhbean.rwsplitting.controller;

import com.zhbean.rwsplitting.bean.ResponseData;
import com.zhbean.rwsplitting.controller.model.Company;
import com.zhbean.rwsplitting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/8/14 11:07
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(name = "/save")
    public ResponseData save(@RequestBody Company company) {
        long startTime = System.currentTimeMillis();
        int add = companyService.add(company);
        long totalTime = System.currentTimeMillis() - startTime;
        return ResponseData.ok(null, totalTime);
    }

    @GetMapping("/list")
    public ResponseData list() {
        long startTime = System.currentTimeMillis();
        Map<String, Object> data = new HashMap<>();
        List<Company> list = companyService.findAll();
        data.put("list", list);

        long totalTime = System.currentTimeMillis() - startTime;
        return ResponseData.ok(data, totalTime);
    }

    @GetMapping("/get/{id}")
    public ResponseData getById(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> data = new HashMap<>();
        List<Company> list = companyService.getById(id);
        data.put("list", list);

        long totalTime = System.currentTimeMillis() - startTime;
        return ResponseData.ok(data, totalTime);
    }


}
