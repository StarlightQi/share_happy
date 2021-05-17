package com.share.happy.model.user;

import com.share.happy.model.BasicsModel;
import lombok.Data;

@Data
public class RegisterUser extends BasicsModel {
    private String username;
    private String userpassword;
    private String useremain;
    private String code;

}
