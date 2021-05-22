/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import io.renren.config.CodeGeneratorProperties;
import io.renren.service.SysGeneratorService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	@Autowired
	private DruidDataSourceAutoConfigure dataSource;
	@Autowired
	private CodeGeneratorProperties properties;
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(String tables, HttpServletResponse response) throws IOException{
		byte[] data = sysGeneratorService.generatorCode(tables.split(","));
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}

	/**
	 * 数据源
	 */
	@ResponseBody
	@RequestMapping("/getDatasource")
	public R getDatasource(){
		DruidDataSource druidDataSource = (DruidDataSource)dataSource.dataSource();
		//System.out.println(data.getRawJdbcUrl());
		properties.setJdbcUrl(druidDataSource.getRawJdbcUrl());
		return R.ok().put("data",properties);
	}

	/**
	 * 数据源
	 */
	@ResponseBody
	@PostMapping("/setDatasource")
	public R setDatasource(@RequestBody CodeGeneratorProperties codeGeneratorProperties) throws SQLException {
		DruidDataSource druidDataSource = (DruidDataSource)dataSource.dataSource();
		if (StringUtils.isNotBlank(codeGeneratorProperties.getJdbcUrl()) && !codeGeneratorProperties.getJdbcUrl().equals(druidDataSource.getRawJdbcUrl())){
			druidDataSource.restart();
			druidDataSource.setUrl(codeGeneratorProperties.getJdbcUrl());
		}
		BeanUtils.copyProperties(codeGeneratorProperties,properties);
		return R.ok("修改成功！");
	}
}
