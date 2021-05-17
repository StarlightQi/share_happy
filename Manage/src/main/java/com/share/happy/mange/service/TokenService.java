package com.share.happy.mange.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

import com.share.happy.model.user.User;
import org.springframework.stereotype.Service;

/***
 * token 下发
* @Title: TokenService.java 
* @author MRC
* @date 2019年5月27日 下午5:40:25 
* @version V1.0
 */
@Service
public class TokenService {

	public String getToken(User user) {
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 24*60* 60 * 1000;//一小时有效时间
		Date end = new Date(currentTime);
		String token = "";
		token = JWT.create().withAudience(user.getUserid()+"").withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(user.getUserpassword()));
		return token;
	}
}
