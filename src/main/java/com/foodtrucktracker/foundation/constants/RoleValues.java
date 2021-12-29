package com.foodtrucktracker.foundation.constants;

public enum RoleValues {
    ADMIN("ADMIN"),
    DINER("DINER"),
    OPERATOR("OPERATOR");

    private String roleName;

    RoleValues(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
