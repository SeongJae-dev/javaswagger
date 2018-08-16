package javaa.swagger.sms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.youiwe.webservice.ServiceSMSSoapProxy;

@Controller
public class SmsContoller {
	
	@RequestMapping("account/send.do")
	@ResponseBody
	public String send(@RequestBody String user_phone)
	{
		System.out.println(user_phone);
		String r="";
		String smsID= "rola";	
		String smsPW="bit123400";
		Random ran = new Random();
		int n =0;
		System.out.println(1);
		while(n<=1000)
		{
			n=ran.nextInt(10000);
		}
		r=n+"";
		System.out.println("r:"+r);
		ServiceSMSSoapProxy sendsms = new ServiceSMSSoapProxy();
		try{
			System.out.println(3);
		ObjectMapper mapper = new ObjectMapper();
		r=mapper.writeValueAsString(r);
		System.out.println("r:"+r);
		String senderPhone= "01036517605";
		String subnum = r.substring(1, r.lastIndexOf("\""));
		
		String receivePhone= user_phone;
		String edem = "EveryDay EveryMoment!\n �ڵ��ȣ�� �������ּ��� :)\n�ڵ��ȣ:";
		String smsContent= edem+subnum;
		String test1 = (smsID+smsPW+receivePhone);
		CEncrypt encrypt = new CEncrypt("MD5",test1);
		java.lang.String send=sendsms.sendSMS(smsID,encrypt.getEncryptData(), senderPhone, receivePhone, smsContent);
 		System.out.println("����ڵ�:"+send);
 		
		}catch(Exception e){
		System.out.println("Exception in main:" +e);
		}
		return r;
		
	}
	
	class CEncrypt
	{
	    MessageDigest md;
	    String strSRCData = "";
	    String strENCData = "";

	    public CEncrypt(){}
	    //�ν��Ͻ� ���� �� �ѹ濡 ó���� �� �ֵ��� ������ �ߺ����׽��ϴ�. 
	    public CEncrypt(String EncMthd, String strData)
	    {
	        this.encrypt(EncMthd, strData);
	    }

	    //��ȣȭ ������ �����ϴ� �޼ҵ��Դϴ�.
	    public void encrypt(String EncMthd, String strData)
	   {
	       try
	      {
	          MessageDigest md = MessageDigest.getInstance(EncMthd); // "MD5" or "SHA1"
	         byte[] bytData = strData.getBytes();
	         md.update(bytData);

	         byte[] digest = md.digest();
	         StringBuffer sb = new StringBuffer();
	         for(int i =0;i<digest.length;i++)
	         {
	        	 strENCData = sb.append(Integer.toString((digest[i]&0xff) + 0x100, 16).substring(1)).toString();
	         }
	       }catch(NoSuchAlgorithmException e)
	      {
	         System.out.print("��ȣȭ �˰����� �����ϴ�.");
	      };
	    
	      //���߿� ���� �����Ͱ� �ʿ����� ���� ������ �Ӵϴ�.
	      strSRCData = strData;
	    }

	    //������ �ζ��� �Լ�(�ƴ�, �޼ҵ�)���Դϴ�.
	    public String getEncryptData(){return strENCData;}
	    public String getSourceData(){return strSRCData;}

	    //�����Ͱ� ������ �����ִ� �޼ҵ��Դϴ�.
	    public boolean equal(String strData)
	    {
	      //��ȣȭ �����Ͷ� �񱳸� �ϴ�, �����̶� �񱳸� �ϴ� �����....
	      if(strData == strENCData) return true;
	      return false;
	    }
		
	}


}
