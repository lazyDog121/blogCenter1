package com.zzteam.blogcenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class userLoginRequest implements Serializable {

    private static final long serialVersionUID = 8278441681648731399L;

    private String userAccount;

    private String userPassword;
}
