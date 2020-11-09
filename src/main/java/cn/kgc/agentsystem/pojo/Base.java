package cn.kgc.agentsystem.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Base {
    private Integer id;
    private Integer starNum;//分页的起始行
    private Integer pageSize;//页显示条数
    private Date startTime; //日期查询起始范围
    private Date endTime;
    private String searchStr;
}
