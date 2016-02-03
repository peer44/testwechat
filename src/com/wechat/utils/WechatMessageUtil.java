package com.wechat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.wechat.entity.vo.wechat.message.TextMessage;

public class WechatMessageUtil {
	// 各种消息类型,除了扫带二维码事件
	/**
	 * 文本消息
	 */
	public static final String MESSAGE_TEXT = "text";
	/**
	 * 图片消息
	 */
	public static final String MESSAtGE_IMAGE = "image";
	/**
	 * 图文消息
	 */
	public static final String MESSAGE_NEWS = "news";
	/**
	 * 语音消息
	 */
	public static final String MESSAGE_VOICE = "voice";
	/**
	 * 视频消息
	 */
	public static final String MESSAGE_VIDEO = "video";
	/**
	 * 小视频消息
	 */
	public static final String MESSAGE_SHORTVIDEO = "shortvideo";
	/**
	 * 地理位置消息
	 */
	public static final String MESSAGE_LOCATION = "location";
	/**
	 * 链接消息
	 */
	public static final String MESSAGE_LINK = "link";
	/**
	 * 事件推送消息
	 */
	public static final String MESSAGE_EVENT = "event";
	/**
	 * 事件推送消息中,事件类型，subscribe(订阅)
	 */
	public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
	/**
	 * 事件推送消息中,事件类型，unsubscribe(取消订阅)
	 */
	public static final String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件推送消息中,上报地理位置事件
	 */
	public static final String MESSAGE_EVENT_LOCATION_UP = "LOCATION";
	/**
	 * 事件推送消息中,自定义菜单事件,点击菜单拉取消息时的事件推送
	 */
	public static final String MESSAGE_EVENT_CLICK = "CLICK";
	/**
	 * 事件推送消息中,自定义菜单事件,点击菜单跳转链接时的事件推送
	 */
	public static final String MESSAGE_EVENT_VIEW = "VIEW";

	/**
	 * 将xml转化为Map集合
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = reader.read(ins);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		try {
			ins.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 文本消息转化为xml
	 * 
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);

	}

}
