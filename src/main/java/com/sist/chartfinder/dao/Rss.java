package com.sist.chartfinder.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*
 * <rss>
 * 	<channel>
 * 	</channel>
 * 	<test1>
 * 	</test1>
 * 	<test2>
 * 	</test2>
 * </rss>
 * */
@XmlRootElement
public class Rss {
	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
