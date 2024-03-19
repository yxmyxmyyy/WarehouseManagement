package com.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenData {
    private String username;
    private Integer roles;
    private Integer warehouseId;
    private String accessToken;
    private String refreshToken;
    private Date expires;
}
