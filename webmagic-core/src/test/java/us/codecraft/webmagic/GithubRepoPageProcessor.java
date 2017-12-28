/**
 * Mainbo.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package us.codecraft.webmagic;

import java.util.List;

import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * <pre>
 *
 * </pre>
 *
 * @author tjx1222
 * @version $Id: GithubRepoPageProcessor.java, v 1.0 2017年1月21日 下午9:23:59 tjx1222 Exp $
 */
public class GithubRepoPageProcessor implements PageProcessor{
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml()
        		.xpath("//table[@width='768']//tr").nodes();
        for(Selectable tr : nodes){
        	String bihuashu = tr.xpath("//p[@class='font_14']/text()").get();
        	List<String> bushou = tr.$("a.fontbox").all();
        	System.out.println(bihuashu+ " " + bushou);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("http://xh.5156edu.com/bs.html").thread(5).run();
    }
}
