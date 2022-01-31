package com.tradeshift.api.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class UpdateParentGroupRequest implements Serializable{


	private static final long serialVersionUID = 1L;

	private String groupType;
	private String targetParentType;

	//Spring framework StringUtils doesn't have isNotBlank method
	public boolean validate() {
		return (((null != groupType) && (!groupType.isEmpty())) 
				&& (((null != targetParentType) && (!targetParentType.isEmpty()))));
	}
}
