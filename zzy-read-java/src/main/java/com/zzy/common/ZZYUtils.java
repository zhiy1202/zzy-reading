package com.zzy.common;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Component
public class ZZYUtils {
    public static Integer getEId(String eid){
        Integer integer = new Integer(eid);
        return integer;
    }
    public static Cookie addCookies(String key,String value){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        cookie.setMaxAge(7*24*60*60);
        return cookie;
    }
    public static String getCookiesValue(HttpServletRequest res,String key){
        Cookie[] cookies = res.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (key.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
    //消除cookies
    public static void exitCookies(HttpServletRequest res){
        Cookie[] cookies = res.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                cookie.setMaxAge(0);
            }
        }
    }

    //生成文件名
    public static String getFileName(String suffix){
        String filePath = "";
        String s = UUID.randomUUID().toString();
        filePath = s +"."+ suffix;
        return filePath;
    }
    //生成文件夹
    public static File createMedioFile(String fileName) throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println("path"+path);
        File file1 = new File(path);
        String path1 = file1.getParentFile().getPath();
        System.out.println("path1"+path1);

        String medioPath = path1 + "\\" + "medio" + "\\" +fileName;
        System.out.println(medioPath);

        File medioPathFile = new File(medioPath);
        if (!medioPathFile.exists()){
            medioPathFile.mkdirs();
        }

        return medioPathFile;

    }
}
