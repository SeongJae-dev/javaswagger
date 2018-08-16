package javaa.swagger.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javaa.swagger.vo.FollowVo;
import javaa.swagger.vo.LikeVo;

public class LikeManager {

	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("javaa/swagger/db/mybatisConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public static List<LikeVo> whoLike(HashMap map){
		SqlSession s = factory.openSession(); 
		List<LikeVo> list = s.selectList("like.whoLike", map); 
		s.close();
		return list;
	}
	
	public static int cntLike(HashMap map){
		SqlSession s = factory.openSession(); 
		int re = s.selectOne("like.cntLike", map);
		s.close();
		return re;
	}
	
	public static int doLike(HashMap map) {
		SqlSession s = factory.openSession(true);
		int re = s.insert("like.doLike", map);
		s.close();
		return re;
	}
	
	public static int cancelLike(HashMap map){
		SqlSession s = factory.openSession(true); 
		int re = s.delete("like.cancelLike", map);
		s.close();
		return re;
	}
	
	public static int getPno(String comment_NO) {
		SqlSession s = factory.openSession(true); 
		int re = s.selectOne("like.getPno", comment_NO);
		s.close();
		return re;
	}

	public static int isLike(HashMap map) {
		SqlSession s = factory.openSession(true); 
		int re = s.selectOne("like.isLike", map);
		s.close();
		return re;
	}
}