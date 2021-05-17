package com.share.happy.mange.service;

import com.share.happy.api.UserApi;
import com.share.happy.common.model.response.CommonCode;
import com.share.happy.common.model.response.QueryResults;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.common.model.response.ResponseResults;
import com.share.happy.dao.ClickLickMapper;
import com.share.happy.dao.SecurityMapper;
import com.share.happy.dao.UserMapper;
//import com.share.happy.mange.ulist.RedisUtil;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.ClickLick;
import com.share.happy.model.Menus;
import com.share.happy.model.Security;
import com.share.happy.model.user.User;
import com.share.happy.model.user.RegisterUser;
import com.share.happy.model.user.UpUserInfo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mobile.device.Device;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.share.happy.mange.ulist.Utils.getResult;

@Service
public class UserServer implements UserApi {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    TokenService tokenService;

    @Autowired
    HttpServletRequest req;

    @Autowired
    private SecurityMapper securityMapper;

    @Autowired
    private ClickLickMapper clickLickMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate redisStringTemplate;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServer(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseResult userLogin(String mail, String password,Device device) {

        User user =userMapper.userLogin(mail);
        if (user==null)
            return new ResponseResult(CommonCode.FAIL);
        if (bCryptPasswordEncoder.matches(password, user.getUserpassword())) {
            String token = tokenService.getToken(user);
            user.setUserpassword("");
            user.setToken(token);
            String ipAddress =Utils.getIpAddr(req);
            String equipment;
            if (device.isMobile()) {
                equipment="手机";
            } else if (device.isTablet()) {
                equipment="平板";
            } else if(device.isNormal()){
                equipment="电脑";
            }else {
                equipment="其他";
            }

            Security security=new Security(user.getUserid()+"",ipAddress,req.getHeader("User-Agent"),equipment,new Date(),new Date());
            securityMapper.addSecurityNapper(security);
            return new ResponseResults<>(CommonCode.SUCCESS,user);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult register(RegisterUser user) {
        if (redisStringTemplate.opsForValue().get(user.getUseremain())==null)
            return new ResponseResults<>(CommonCode.FAIL, "请先获取邮箱验证码");
        System.out.println(redisStringTemplate.opsForValue().get(user.getUseremain()));
        System.out.println(user.getCode());
        if (!redisStringTemplate.opsForValue().get(user.getUseremain()).equals(user.getCode()))
            return new ResponseResults<>(CommonCode.FAIL, "验证码验证错误请重新输入");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserpassword(bCryptPasswordEncoder.encode(user.getUserpassword()));
        return getResult(userMapper.insert(user));
    }

    @Override
    public ResponseResult getUserInfo() {
        User user =userMapper.findUserId(Utils.getTokenUserMain());
        user.setUserpassword(null);
        return new ResponseResults<>(CommonCode.SUCCESS,user);
    }

    @Override
    public ResponseResult updateUserInfo(UpUserInfo user) {
        user.setUpdatetime(new Date());
        user.setUserid(Utils.getTokenUserMain());
        return getResult(userMapper.upUserInfo(user));
    }

    @Override
    public ResponseResult updateUserPassword(String oldPassword, String password) {
        User user =userMapper.findUserId(Utils.getTokenUserMain());

        if (bCryptPasswordEncoder.matches(oldPassword, user.getUserpassword())) {
            if (userMapper.upUserPassword(Utils.getTokenUserMain(),bCryptPasswordEncoder.encode(password),new Date())==0)
                return new ResponseResult(CommonCode.FAIL);
            else {
                //重新获取数据库里边的密码
                String token = tokenService.getToken(userMapper.findUserId(Utils.getTokenUserMain()));
                return new ResponseResults<>(CommonCode.SUCCESS, token);
            }
        }
       return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public ResponseResult getMenus() {
        ArrayList<Menus> menus=new ArrayList<>();
        String uid=Utils.getTokenUserMain();
        String perMission=userMapper.getUserPerMission(uid);
        ArrayList<Menus> menusArrayList1=new ArrayList<>();
        menusArrayList1.add(new Menus("121","论坛浏览","show",null));
        menusArrayList1.add(new Menus("122","修改信息","modUser",null));
        menusArrayList1.add(new Menus("123","安全设置","security",null));

        ArrayList<Menus> menusArrayList2=new ArrayList<>();
        menusArrayList2.add(new Menus("131","文章发表","sendArticle",null));
        menusArrayList2.add(new Menus("133","文章回收站","reArticle",null));

        ArrayList<Menus> menusArrayList3=new ArrayList<>();
        menusArrayList3.add(new Menus("141","积分交易","coreBuy",null));

        ArrayList<Menus> menusArrayList4=new ArrayList<>();
        menusArrayList4.add(new Menus("151","灵感来源","mid",null));
        menusArrayList4.add(new Menus("152","活动中心","ati",null));

        ArrayList<Menus> menusArrayList5=new ArrayList<>();
        menusArrayList5.add(new Menus("161","文章数据统计","sti",null));
        menusArrayList5.add(new Menus("162","数据详情","details",null));



        Menus menus1=new Menus("120","个人信息","useInfo",menusArrayList1);
        Menus menus2=new Menus("130","文章管理","article",menusArrayList2);
        Menus menus3=new Menus("140","积分商城","core",menusArrayList3);
        Menus menus4=new Menus("150","数据来源","formData",menusArrayList4);// 灵感来源，获取论坛的一些活动
        Menus menus5=new Menus("160","数据中心","dataInto",menusArrayList5);

        menus.add(menus1);
        menus.add(menus2);
        menus.add(menus3);
        menus.add(menus4);
        menus.add(menus5);
        if (perMission.equals("3"))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您还不是作者，占时无法发布文章！");
        else if (perMission.equals("1")) {
            ArrayList<Menus> menusArrayList6=new ArrayList<>();
            menusArrayList6.add(new Menus("171","用户管理","userAdmin",null));
            menusArrayList6.add(new Menus("172","数据统计","dataInfo",null));
            Menus menus6=new Menus("170","论坛管理","luAdmin",menusArrayList6);
            menus.add(menus6);
        }

        return new QueryResults<>(CommonCode.SUCCESS,menus);
    }


    public ResponseResult uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseResults<>(CommonCode.FAIL,"没有文件");

        }
        String filePath = new File("D:\\static\\image").getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        String lastNam=Utils.lastName(file.getOriginalFilename());

        assert lastNam != null;
        if (lastNam.equals("png")||lastNam.equals("jpg")) {
            String name;
            if (req.getHeader("text")==null||req.getHeader("text").equals(""))
                name = file.getOriginalFilename();
            else
                name = System.currentTimeMillis()+"."+lastNam;


            fileUpload = new File(filePath, name);
            try {
                file.transferTo(fileUpload);
                return new ResponseResults<>(CommonCode.SUCCESS, name);
            } catch (IOException e) {
                return new ResponseResults<>(CommonCode.FAIL, "其他原因");
            }
        }else {
            return new ResponseResults<>(CommonCode.FAIL,"请上传指定的格式的文件");
        }
    }

    @Override
    public ResponseResult getSecurity() {
    return new QueryResults<>(CommonCode.SUCCESS,securityMapper.getSecurity(Utils.getTokenUserMain()));
    }

    @Override
    public ResponseResult clickLick(ClickLick clickLick) {
        clickLick.setLickuId(Utils.getTokenUserMain());
        return Utils.getResult(clickLickMapper.clickLick(clickLick));
    }

    @Override
    public ResponseResult isLick(String aid) {
        if (clickLickMapper.isLick(Utils.getTokenUserMain(),aid)==null)
            return new ResponseResult(CommonCode.FAIL);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult CancelClick(String aid) {
        return Utils.getResult(clickLickMapper.CancelClick(Utils.getTokenUserMain(),aid));
    }

    @Override
    public ResponseResult sendCode(String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("2723132855@qq.com","Java学习论坛");
        mimeMessageHelper.setTo(email);
        String data=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String code=Utils.getRandomString(5);
        mimeMessageHelper.setSubject("【java学习论坛】您于"+data+"申请的学习论坛的验证码");
        if (redisStringTemplate.opsForValue().get(email)!=null)
            return new ResponseResults<>(CommonCode.FAIL, "邮箱已经发送请3分钟后再试");
            redisStringTemplate.opsForValue().set(email,code,60*3,TimeUnit.SECONDS);
            String html="<div style='text-alter:center'>【java学习论坛】您于"+data+"申请的学习论坛的验证码</div><div style=\"width: 600px;height: 180px;margin: auto;border: rgba(102,102,102,0.25) solid 1px\">"+
                    "<div style=\"color: white;background-color: rgba(15,118,255,0.75);text-indent: 20px;line-height: 36px;font-size: 22px\">邮箱验证码"+
                    "</div><div style=\"text-indent: 30px;margin-top: 60px\">您的邮箱验证码：" +
                    "<a style=\"color: #0f76ff\">"+code+"</a>（3分钟内有效）"+
                    "</div></div>";
            mimeMessageHelper.setText(html, true);
            javaMailSender.send(mimeMessage);
            return new ResponseResults<>(CommonCode.SUCCESS,"邮箱发送成功！");
    }


}
