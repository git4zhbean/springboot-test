package com.zhbean.rwsplitting.service;

import com.zhbean.rwsplitting.mapper.CompanyMapper;
import com.zhbean.rwsplitting.controller.model.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/7/17 15:10
 */
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int add(Company company) {
        int insert = companyMapper.insert(company);
        log.info("成功导入%d条数据", insert);
        return insert;
    }

    @Override
    public int update(Company company) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public List<Company> getById(Integer id) {
        List<Company> list = new ArrayList<>();
        Company company = companyMapper.selectByPrimaryKey(id);
        list.add(company);
        return list;
    }

    @Override
    public List<Company> findAll() {
        return companyMapper.selectAll();
    }
}
