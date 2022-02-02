package com.meydan.editor;
import java.beans.PropertyEditorSupport;

public class MeydanEditor extends PropertyEditorSupport  {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if (text.equals("1")) {
			
			setValue(true);
		}
		else if (text.equals("2")) {
			setValue(false);
		}
		
	}
}
