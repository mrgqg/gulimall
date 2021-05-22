package io.renren.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description
 * @Author Mrguo
 * @Email 948485649@qq.com
 * @Date 2021/5/22 16:47
 */
@ConfigurationProperties
@Component("dataSourceProperties")
@PropertySource(value="classpath:generator.properties")
public class CodeGeneratorProperties implements Serializable {
    private String jdbcUrl;
    private String mainPath;
    //包名
    @Value("${package}")
    private String packageName;
    private String moduleName;
    private String author;
    private String email;
    private String tablePrefix;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public String toString() {
        return "DataSourceProperties{" +
                "mainPath='" + mainPath + '\'' +
                ", packageName='" + packageName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                ", tablePrefix='" + tablePrefix + '\'' +
                '}';
    }
}
