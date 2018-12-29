package souvc.weixin.main;

import java.io.IOException;

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
 * 菜单管理器类
 * @author xjw
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static void main(String []args) throws IOException{
		//第三方用户唯一凭证
		String appId ="wx0e14fbcce2b76485";
		String appSecret="e8cc964e10bce524ddefb25ef8f7a421";
		
		//调用获取Access_token
		Token token = CommonUtil.getToken(appId, appSecret);
		
		if(null != token){
			//调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), token.getAccessToken());
			//判断菜单创建结果
			if(0==result){
				log.info("菜单创建成功");
			}else{
				log.info("菜单创建失败，错误码："+result);
			}
			
		}
		System.in.read();
		
	}
	/**
	 * 组装菜单数据
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setKey("weather");
		btn11.setType("click");
		btn11.setKey("11");
		
		CommonButton btn12 = new CommonButton();
		btn12.setKey("bus");
		btn12.setType("click");
		btn12.setKey("12");
		
		 CommonButton btn13 = new CommonButton();
        btn13.setName("sosuo");
        btn13.setType("click");
        btn13.setKey("13");

        CommonButton btn14 = new CommonButton();
        btn14.setName("historyToday");
        btn14.setType("click");
        btn14.setKey("14");

        CommonButton btn21 = new CommonButton();
        btn21.setName("Music");
        btn21.setType("click");
        btn21.setKey("21");

        CommonButton btn22 = new CommonButton();
        btn22.setName("GoodGame");
        btn22.setType("click");
        btn22.setKey("22");

        CommonButton btn23 = new CommonButton();
        btn23.setName("meinvDiantai");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn24 = new CommonButton();
        btn24.setName("renLianshibie");
        btn24.setType("click");
        btn24.setKey("24");

        CommonButton btn25 = new CommonButton();
        btn25.setName("Liaotianlaoke");
        btn25.setType("click");
        btn25.setKey("25");

        CommonButton btn31 = new CommonButton();
        btn31.setName("Qyouquan");
        btn31.setType("click");
        btn31.setKey("31");

        CommonButton btn32 = new CommonButton();
        btn32.setName("MOvie");
        btn32.setType("click");
        btn32.setKey("32");

        CommonButton btn33 = new CommonButton();
        btn33.setName("JOKE");
        btn33.setType("click");
        btn33.setKey("33");
		
        /**
         * 微信，mainbtn1,mainbtn2,mainbtn3底部三个一级菜单
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        ComplexButton mainBtn2 = new ComplexButton();
        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn1.setName("zhushou");
       /* mainBtn1.setKey("1");
        mainBtn1.setType("click");*/
        
        mainBtn1.setSub_button(new CommonButton[]{btn11,btn12});
        
        
        mainBtn2.setName("休闲驿站");
        //mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });
        
        mainBtn3.setName("更多体验");
       // mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });
        
        Menu menu = new Menu();
        CommonButton btn = new CommonButton();
        btn.setKey("11");btn.setName("test");btn.setType("click");
        CommonButton btn1 = new CommonButton();
        btn1.setKey("12");btn1.setName("test1");btn1.setType("click");
        //menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});
        menu.setButton(new Button[]{btn});
		return menu;
		
	}
	
}
