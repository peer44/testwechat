package com.wechat.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.service.wechat.WechatService;
import com.wechat.utils.CheckUtil;

@Controller
public class WechatController {

	private static Logger log = Logger.getLogger(WechatController.class);
	@Autowired
	WechatService wechatService;

	/**
	 * 验证微信服务器
	 * 
	 * @param response
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	public void wechatService(PrintWriter out, HttpServletResponse response,
			@RequestParam(value = "signature", required = false) String signature, @RequestParam String timestamp,
			@RequestParam String nonce, @RequestParam String echostr) {
		log.info("signature：" + signature + "\ntimestamp：" + timestamp + "\nnonce：" + nonce + "\nechostr：" + echostr);
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			log.info(echostr);
			out.print(echostr);
		}
	}

	/**
	 * 接收来自微信发来的消息
	 * 
	 * @param out
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/wechat", method = RequestMethod.POST)
	public void wechatServicePost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		String responseMessage = wechatService.processRequest(request);
		out.print(responseMessage);
		out.flush();
	}

}
