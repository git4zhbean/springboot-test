package com.zhbean.rwsplitting.service;

import com.zhbean.rwsplitting.controller.model.Company;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: zhbean
 * @Date: 2019/7/17 15:05
 */
public interface CompanyService {
    int add(Company company);

    int update(Company company);

    int delete(Integer id);

    List<Company> getById(Integer id);

    List<Company> findAll();

}
