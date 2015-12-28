package com.wheel.mybatis.model;

import org.hibernate.validator.constraints.NotBlank;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name = "wh_user")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotBlank
    @Size(min=3, max=15)
    @Pattern(regexp = "^[a-zA-Z_][\\w]{4,19}$", message="{user.name.pattern.error}")
    private String account;

    @Column(name = "nick_name")
    private String nickName;

    @Size(min=6, max=25,message="{user.password.length.error}")
    private String password;

    @Column(name = "regist_time")
    private Date registTime;

    private String salt;

    public User(){
        super();
    }

    public User(String account,String  password,String nickName){
        this.account = account;
        this.password = password;
        this.nickName = nickName;
    }
    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return is_active
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return regist_time
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * @param registTime
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getCredentialsSalt() {
        return account + salt;
    }
}