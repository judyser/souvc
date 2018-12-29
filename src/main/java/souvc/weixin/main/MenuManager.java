package souvc.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import souvc.weixin.menu.Button;
import souvc.weixin.menu.CommonButton;
import souvc.weixin.menu.ComplexButton;
import souvc.weixin.menu.Menu;
import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.TokenUtil;
import souvc.weixin.util.WeixinUtil;

/**
 * �˵���������
 * @author xjw
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static void main(String []args){
		//�������û�Ψһƾ֤
		String appId ="wx0e14fbcce2b76485";
		String appSecret="e8cc964e10bce524ddefb25ef8f7a421";
		
		//���û�ȡAccess_token
		Token token = CommonUtil.getToken(appId, appSecret);
		
		if(null != token){
			//���ýӿڴ����˵�
			int result = WeixinUtil.createMenu(getMenu(), token.getAccessToken());
			//�жϲ˵��������
			if(0==result){
				log.info("�˵������ɹ�");
			}else{
				log.info("�˵�����ʧ�ܣ������룺"+result);
			}
		}
	}
	/**
	 * ��װ�˵�����
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setKey("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");
		
		CommonButton btn12 = new CommonButton();
		btn12.setKey("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");
		
		 CommonButton btn13 = new CommonButton();
        btn13.setName("�ܱ�����");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("��ʷ�ϵĽ���");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("�����㲥");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("������Ϸ");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("��Ů��̨");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("����ʶ��");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("�������");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Q��Ȧ");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("��Ӱ���а�");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("��ĬЦ��");
        btn33.setType("click");
        btn33.setKey("33");
		
        /**
         * ΢�ţ�mainbtn1,mainbtn2,mainbtn3�ײ�����һ���˵�
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        ComplexButton mainBtn2 = new ComplexButton();
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn1.setName("��������");
        mainBtn1.setSub_button(new CommonButton[]{btn11,btn12,btn13,btn14});
        
        
        mainBtn2.setName("������վ");
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });
        
        mainBtn3.setName("��������");
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
        
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
		return menu;
	}
	
}
