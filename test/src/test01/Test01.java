package test01;

import java.security.GeneralSecurityException;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String contents = "�ȴ��ϼ��� <a href='search.do?searchType=hash&tag=����'>#����</a> �۸����Դϴ�.";
		String[] arrContents = contents.split(" ");
		String[] arrHash = null;
		for(int i = 0; i < arrContents.length; i++) {
			if(arrContents[i].contains("#")) {
				arrHash = arrContents[i].split("#");
				for(int j = 0; j < arrHash.length; j++) {
					if(!arrHash[j].trim().isEmpty()) {
						contents = contents.replace("#" + arrHash[j], "<a href='search.do?searchType=hash&tag=" + arrHash[j].trim() + "'>#" + arrHash[j].trim() + "</a>");
					}
				}
			}
		}
		int []arr = {1,2,3,4,5};
		int n = 3;
		int idx = getIdx(arr, n);
		System.out.println(idx);
		
	}
	
	public static int getIdx(int []arr, int n) throws ArrayIndexOutOfBoundsException{
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
