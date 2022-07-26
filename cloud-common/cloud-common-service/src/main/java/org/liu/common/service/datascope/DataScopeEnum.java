package org.liu.common.service.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author
 * <p>
 * 数据权限类型
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
	/**
	 * 查询全部数据
	 */
	ALL(0, "全部"),

	/**
	 * 本级及子级
	 */
	OWN_CHILD_LEVEL(1, "本级及子级"),

	/**
	 * 本级
	 */
	OWN_LEVEL(2, "本级"),

	/**
	 * 自定义
	 */
	CUSTOM(3, "自定义");

	/**
	 * 类型
	 */
	private final int type;
	/**
	 * 描述
	 */
	private final String description;
}
