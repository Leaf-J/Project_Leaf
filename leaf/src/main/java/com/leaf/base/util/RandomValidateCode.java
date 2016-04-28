package com.leaf.base.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

public class RandomValidateCode {

	public static final String RANDOMCODEKEY = "REGISTER_RAMDOMCODE";
	private Random random = new Random();
	private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //随机产生的字符串
	
	private static int width = 90;//图片宽
	private static int height = 25;//图片高
	private static int lineSize = 80;//干扰线数量
	private static int stringNum = 4;//随机产生字符数量
	
	/*
	 * 获得字体
	 */
	private Font getFont(){
		return new Font("Fixedsys",Font.CENTER_BASELINE,18);
	}
	
	/*
	 * 获得颜色
	 */
	private Color getRandomColor(int fc,int bc){
		if(fc>255){
			fc = 255;
		}
		if(bc>255){
			bc = 255;
		}
		int r = fc + random.nextInt(bc-fc-16);
		int g = fc + random.nextInt(bc-fc-14);
		int b = fc + random.nextInt(bc-fc-18);
		return new Color(r,g,b);
	}
	
	/*
	 * 生成随机图片
	 */
	public void getRandCode(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		//BufferedImage类是具有缓冲区的Image类，Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();//产生Image对象的Graphic
		//设置背景色
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
		g.setColor(this.getRandomColor(110,133));
		
		//绘制干扰线
		for(int i=0;i<lineSize;i++){
			drawLine(g);
		}
		//绘制随机字符
		String randomString = "";
		for(int i=0;i<stringNum;i++){
			randomString = drawString(g,randomString,i);
		}
		
		session.removeAttribute(RANDOMCODEKEY);
		session.setAttribute(RANDOMCODEKEY, randomString);
		g.dispose();
		
		try {
			ImageUtil.writeToString(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 绘制字符串
	 */
	private String drawString(Graphics g,String randomString,int i){
		g.setFont(getFont());
		g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
		String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
		randomString += rand;
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(rand, 23*i, 16);
		return randomString;
	}
	
	/*
	 * 绘制干扰线
	 */
	private void drawLine(Graphics g){
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x1 = random.nextInt(13);
		int y1 = random.nextInt(15);
		g.drawLine(x, y, x+x1, y+y1);
	}
	
	/*
	 * 获取随机字符
	 */
	public String getRandomString(int num){
		return String.valueOf(randString.charAt(num));
	}
	
	//Patchca solution start
	private static final ConfigurableCaptchaService cs = new ConfigurableCaptchaService(); 
	private static Random staticRandom = new Random();
	static{
		cs.setColorFactory(new ColorFactory() {
			
			@Override
			public Color getColor(int x) {
				int[] c = new int[3];
				int i = staticRandom.nextInt(c.length);
				for(int fi=0; fi < c.length ; fi++){
					if(fi == i){
						c[fi] = staticRandom.nextInt(71);
					}else{
						c[fi] = staticRandom.nextInt(256);
					}
				}
				return new Color(c[0],c[1],c[2]);
			}
		});
		
		RandomWordFactory wf = new RandomWordFactory();
		wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		cs.setWordFactory(wf);
	}
	
	public void getRandCodeWithPatchca(HttpServletRequest request, HttpServletResponse response) throws IOException{
		switch(staticRandom.nextInt(5)){
		case 0:
			cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
			break;
		case 1:
			cs.setFilterFactory(new MarbleRippleFilterFactory());
			break;
		case 2:
			cs.setFilterFactory(new DoubleRippleFilterFactory());
			break;
		case 3:
			cs.setFilterFactory(new WobbleRippleFilterFactory());
			break;
		case 4:
			cs.setFilterFactory(new DiffuseRippleFilterFactory());
			break;
		}
		HttpSession session = request.getSession();
		setResponseHeaders(response);
		String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
		session.setAttribute(RANDOMCODEKEY, token);
	}
	
	protected void setResponseHeaders(HttpServletResponse response){
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "on-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		long time = System.currentTimeMillis();
		response.setDateHeader("Last-Modified", time);
		response.setDateHeader("Date", time);
		response.setDateHeader("Expires", time);
	}
	//Patchca solution end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
