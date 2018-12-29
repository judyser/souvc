package souvc.weixin.menu;

/**
 * 菜单项的基类
 * @author xjw
 *
 */
public class Button {
	private String name;//所有一级菜单、二级菜单公用一个相同的属性

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
