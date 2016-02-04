package com.wechat.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController {
	@Autowired
	private Producer captchaProducer;

	/**
	 * 返回验证码的图片流
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param timestamp
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCaptchaImage.jpg", method = RequestMethod.GET)
	public ModelAndView getCaptchaImage(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "timestamp", required = false) String timestamp) throws IOException {
		//时间戳目前并没有用到，主要是在无状态的情况下，通过一个id来识别验证码，到时候可以用uuid
		if (StringUtils.isEmpty(timestamp)) {
			System.out.println("没有时间戳\ttimestamp:" + timestamp);
			model.addAttribute("timestamp", System.currentTimeMillis());
		} else {
			System.out.println("有时间戳\ttimestamp:" + timestamp);
			model.addAttribute("timestamp", timestamp);
		}
		HttpSession session = request.getSession();
		//String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//System.out.println("验证码: " + code);

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		System.out.println("capText:"+capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	/**
	 * 页面入口
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getCaptcha", method = RequestMethod.GET)
	public String getCaptcha(Model model) {
		model.addAttribute("timestamp", System.currentTimeMillis());
		return "captcha";
	}

	/**
	 * 检测验证码是否输入正确
	 * @param session
	 * @param model
	 * @param timestamp
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST)
	@ResponseBody
	public String checkCaptcha(HttpSession session, Model model,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "code", required = false) String code) {
		boolean returnStr = false;
		System.out.println("timestamp = " + timestamp);
		System.out.println("code = " + code);
		String original = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println("original = " + original);
		if (StringUtils.isNotEmpty(code)) {
			if (code.equalsIgnoreCase(original)) {
				returnStr = true;
			}
		}
		return returnStr + "";

	}

}
