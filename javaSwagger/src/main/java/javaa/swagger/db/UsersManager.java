package javaa.swagger.db;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javaa.swagger.vo.UsersVo;


public class UsersManager {
	private static SqlSessionFactory factory;
	static {
		try {
		Reader reader = Resources.getResourceAsReader("javaa/swagger/db/mybatisConfig.xml");
		factory = new SqlSessionFactoryBuilder().build(reader);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//ȸ������ �޼ҵ� 
	public static int insertUser(UsersVo u)
	{
		
		int re=-1;
		SqlSession session = factory.openSession();
		re=session.insert("users.insert",u);
		if(re>=1)
		{
			session.commit();
		}
		session.close();
		return re;
	}
	//�α��� �޼ҵ�
	public static boolean isUser(Map map)
	{
		boolean r = false;
		
		SqlSession session = factory.openSession();
		UsersVo u=session.selectOne("users.isUser",map);
		
		if(u!=null)
		{
			r=true;
		}
		session.close();
		
		return r;
		
	}
	//�ߺ��˻��� �� �ֵ��� ID�� �ҷ����� �޼ҵ�
		public static int idCheck(String user_id) {
			// TODO Auto-generated method stub
			SqlSession session =factory.openSession();
			int re = session.selectOne("users.idCheck",user_id);
			session.close();
			
			return re;
	}
	
	//������ ���� �޼ҵ�
	public static UsersVo profile(Map map) {
		// TODO Auto-generated method stub
		SqlSession session =factory.openSession();
		UsersVo u = session.selectOne("users.profile", map);
		session.close();
		
		return u;
	}
	//������ ����
	public static int editProfile(UsersVo u) {
		int re=-1;
		
		SqlSession session = factory.openSession();
		re=session.update("users.edit", u);
		if(re>0)
		{
			session.commit();
		}
		session.close();

		return re;
	}
	public static int withdrawUser(Map map) {
		// TODO Auto-generated method stub
		
		int re =-1;
		
		SqlSession session = factory.openSession();
		re=session.delete("users.withDraw", map);
		if(re>0)
		{
			session.commit();
		}
		session.close();
		
		return re;
	}
	public static List<UsersVo> search(Map map) {

		SqlSession session = factory.openSession();	
		List<UsersVo> list = session.selectList("users.search", map);
		session.close();
		
		return list;
	}
	
}
