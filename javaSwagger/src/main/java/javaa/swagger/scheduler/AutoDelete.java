package javaa.swagger.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javaa.swagger.dao.CommentDao;
import javaa.swagger.dao.LikeDao;
import javaa.swagger.dao.PostDao;

@Component
public class AutoDelete {
	@Autowired
	PostDao pdao;
	
	@Autowired
	CommentDao cdao;
	
	@Autowired
	LikeDao ldao;

	public void setPdao(PostDao pdao) {
		this.pdao = pdao;
	}

	public void setCdao(CommentDao cdao) {
		this.cdao = cdao;
	}

	public void setLdao(LikeDao ldao) {
		this.ldao = ldao;
	}
	
	Date today = new Date();
	
	@Scheduled(cron=" 0 * * * * * ")
	public void autoDelete() {
		int c = cdao.autoDelete();
		int l = ldao.autoDelete();
		int p = pdao.autoDelete();
		System.out.println(today.toString() + " : " +  p + "���� �Խù��� ���õ� " + c + "���� ��۰� " + l + "���� ���ƿ� ������ �����Ǿ����ϴ�.");
	}
}
