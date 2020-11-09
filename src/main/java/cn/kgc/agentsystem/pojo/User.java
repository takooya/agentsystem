package cn.kgc.agentsystem.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "as_user")
public class User extends Base {
    private String userCode;
    private String userName;
    private String userPassword;
    private Date creationTime;
    private Date lastLoginTime;
    private String createdBy;
    private Date lastUpdateTime;
    private Integer isStart;
    private Integer roleId;
    private String roleName;
}
