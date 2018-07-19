package javaa.swagger.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import javaa.swagger.db.UsersManager;
import javaa.swagger.vo.UsersVo;

@Repository
public class UsersDao {

	//ȸ������ 
	public int insertUser(UsersVo u)
	{
		return UsersManager.insertUser(u);
	}
	
	//�α���
	public boolean isUser(Map map)
	{
		return UsersManager.isUser(map);
	}
}
