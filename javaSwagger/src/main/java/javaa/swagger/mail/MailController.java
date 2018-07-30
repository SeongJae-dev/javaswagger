package javaa.swagger.mail;

import java.util.Random;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javaa.swagger.vo.UsersVo;

@Controller
public class MailController {

	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		System.out.println("setter�� ������");
		this.mailSender = mailSender;
	}

	//email Ȯ��-------------------------------------------------------
	@RequestMapping(value="account/mailTest.do",method=RequestMethod.GET)
	public ModelAndView emailcheck(String user_emil)
	{
		ModelAndView mav = new ModelAndView();
	
		Random random = new Random();
			int code = 0;
			
			while(true) {
				code = random.nextInt(999999);
				if(code > 100000) {
					break;
				}
			}
			mav.addObject("code",code);
			//mav.addObject("user_email",user_email);
			
			try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setSubject("eden ȸ����� ���� �̸��Ϲ߼�");
			mailMessage.setFrom("sanghyupz@naver.com");
			mailMessage.setText("����� �ڵ�� "+ code +"�Դϴ�. �������� �ڵ带 �Է��ϼ���");
			mailMessage.setTo(user_emil);
			
			System.out.println("user_emil:"+user_emil);
			System.out.println("�ڵ�:"+code);
			
			mailSender.send(mailMessage);
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}	
			
			return mav;
	}
	
	@RequestMapping(value="account/confirm.do")
	public ModelAndView check(int input, int code) {
		ModelAndView mav = new ModelAndView();
		String msg = "";
		
		if(input == code) {
			msg = "������������!";
			mav.setViewName("account/create");
			
		} else {
			msg = "ȸ������ ����! �ڵ尡 Ʋ�Ƚ��ϴ�.";
		}
		
		mav.addObject("msg",msg);
		
		return mav;
	}//email Ȯ��-------------------------------------------------------
}
