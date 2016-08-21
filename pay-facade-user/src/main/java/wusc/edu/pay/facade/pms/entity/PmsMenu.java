package wusc.edu.pay.facade.pms.entity;

import wusc.edu.pay.common.entity.BaseEntity;

/**
 * <ul>
 * <li>Title: 系统菜单</li>
 * <li>Description:</li>
 * <li>Copyright: www.gzzyzz.com</li>
 * <li>Company:</li>
 * </ul>
 * 
 * @author Hill
 * @version 2013-11-12
 */
public class PmsMenu extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/** 菜单名称 **/
	private String name;
	
	/** 菜单地址 **/
	private String url;

	/** 菜单编号（用于显示时排序） **/
	private String number;
	
	/** 是否为叶子节点 **/
	private boolean isLeaf;
	
	/** 菜单层级 **/
	private Long level;
	
	/** 父节点:一级菜单为0 **/
	private PmsMenu parent;
	
	/** 目标名称（用于DWZUI的NAVTABID） **/
	private String targetName;
	
	

	public PmsMenu() {
		super();
	}

	
	/** 菜单名称 **/
	public String getName() {
		return name;
	}

	/** 菜单名称 **/
	public void setName(String name) {
		this.name = name;
	}

	/** 菜单地址 **/
	public String getUrl() {
		return url;
	}

	/** 菜单地址 **/
	public void setUrl(String url) {
		this.url = url;
	}

	/** 菜单编号（用于显示时排序） **/
	public String getNumber() {
		return number;
	}

	/** 菜单编号（用于显示时排序） **/
	public void setNumber(String number) {
		this.number = number;
	}

	/** 是否为叶子节点 **/
	public boolean getIsLeaf() {
		return isLeaf;
	}

	/** 是否为叶子节点 **/
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/** 菜单层级 **/
	public Long getLevel() {
		return level;
	}

	/** 菜单层级 **/
	public void setLevel(Long level) {
		this.level = level;
	}

	/** 父节点:一级菜单为0 **/
	public PmsMenu getParent() {
		return parent;
	}

	/** 父节点:一级菜单为0 **/
	public void setParent(PmsMenu parent) {
		this.parent = parent;
	}

	/** 目标名称（用于DWZUI的NAVTABID） **/
	public String getTargetName() {
		return targetName;
	}

	/** 目标名称（用于DWZUI的NAVTABID） **/
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}


}
