package com.wheel.mybatis.model;

import javax.persistence.*;

@Table(name = "wh_role_permission_link")
public class RolePermissionLink {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return permission_id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}