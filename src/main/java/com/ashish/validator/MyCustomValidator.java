package com.ashish.validator;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.util.StringUtils;

public class MyCustomValidator extends CustomNumberEditor
{
	public MyCustomValidator(Class<? extends Number> number) throws IllegalArgumentException
	{
		super(number,true);
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		if (!StringUtils.hasText(text)) 
		{
            setValue(0);
        }
		else
		{
            super.setAsText(text.trim());
        }
	}
}
