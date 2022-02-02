package com.meydan.editor;

import java.beans.PropertyEditorSupport;

public class MeydanEditorForTel extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		int parseInt = Integer.parseInt(text);
		
		setValue(parseInt);
		
	}

	
	
}
