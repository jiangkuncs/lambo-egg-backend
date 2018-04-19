package com.lambo.ndp.servlet;

import com.alibaba.fastjson.JSONObject;
import com.lambo.ndp.constant.IdentCode;
import com.lambo.ndp.constant.IdentCodeConstant;
import com.lambo.ndp.constant.MoniterConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IdentityServlet extends HttpServlet {

    private static final long serialVersionUID = -479885884254942306L;

    public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static Random random = new Random();

    public static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random
                .nextInt(255));
    }

    public static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c
                .getBlue());
    }

    public void createIdentCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("image/jpeg");

        String randomString = getRandomString();
        String userName = getUserName();
        request.getSession(true).setAttribute(userName, randomString);

        int width = 100;
        int height = 30;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        g.drawString(randomString, 18, 20);
        for (int i = 0, n = random.nextInt(100); i < n; i++) {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        // 转成JPEG格式
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpeg", out);
        out.flush();
    }

    public void identCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String code = request.getParameter("code");
        String userName = getUserName();
        String randomString = (String) request.getSession(true).getAttribute(userName);
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isNotBlank(randomString) && StringUtils.isNotBlank(code)
                && randomString.equalsIgnoreCase(code)){
            if(MoniterConstants.visitMap == null){
                MoniterConstants.visitMap = new HashMap();
            }
            if(StringUtils.isNotBlank(userName)){
                MoniterConstants.visitMap.put(userName,0);
            }
            jsonObject.put("code",IdentCodeConstant.IDENT_CODE_CORRECT.code);
            jsonObject.put("data",1);
        }else{
            jsonObject.put("code",IdentCodeConstant.IDENT_CODE_ERROR.code);
            jsonObject.put("data",0);
        }
        PrintWriter out = response.getWriter();
        out.write(jsonObject.toString());
        out.close();
    }

    public String getUserName(){
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userName == null ? "" : userName;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        if(null != type && "1".equals(type)){
            identCode(request,response);
        }else{
            createIdentCode(request,response);
        }
    }

}
