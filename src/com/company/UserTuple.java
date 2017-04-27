package com.company;

/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class UserTuple {
    private long userId;
    private UserType userType;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

