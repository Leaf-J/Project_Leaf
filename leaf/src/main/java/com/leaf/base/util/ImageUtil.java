package com.leaf.base.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()*times;
        int height = originalImage.getHeight()*times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * 对图片进行放大
     * @param srcPath 原始图片路径(绝对路径)
     * @param newPath 放大后图片路径（绝对路径）
     * @param times 放大倍数
     * @return 是否放大成功
     */
    public static boolean zoomInImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomInImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }
    /**
     * 对图片进行缩小
     * @param originalImage 原始图片
     * @param times 缩小倍数
     * @return 缩小后的Image
     */
    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()/times;
        int height = originalImage.getHeight()/times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * 对图片进行缩小
     * @param srcPath 源图片路径（绝对路径）
     * @param newPath 新图片路径（绝对路径）
     * @param times 缩小倍数
     * @return 保存是否成功
     */
    public static boolean zoomOutImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomOutImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }
    
    
    public static void writeImage(InputStream src,File des ,int width,int height) throws IOException{
    	
    	if(src==null||des==null ){
    		throw new IllegalArgumentException("src or des must not be null!");
    	}
    	
    	BufferedImage originalImage = ImageIO.read(src);
    	
    	int colorType=originalImage.getType();
    	
    	if(originalImage.getType()==BufferedImage.TYPE_CUSTOM){
    		colorType=BufferedImage.TYPE_INT_BGR;
    	}
    	
    	BufferedImage newImage = new BufferedImage(width,height,colorType);
    	
    	Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        
        ImageIO.write(newImage, des.getName().substring(des.getName().lastIndexOf(".")+1), des);
    	
    }
    
    public static void writeToString(RenderedImage image,String format,OutputStream outputStream) throws IOException{
    	ImageIO.write(image, format, outputStream);
    }
}
