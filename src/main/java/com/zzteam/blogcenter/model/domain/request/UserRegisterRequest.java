package com.zzteam.blogcenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -5039559677556245275L;

    private String userAccount;

    private String userPassword;
}
