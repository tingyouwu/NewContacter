package com.future.wk.newcontacter.base.store.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DatabaseField {

	FieldType Type() default FieldType.VARCHAR;

	boolean primaryKey() default false;

	boolean isLongType() default false;//用来区分int 型和 long型

	int isYellowPage() default 0;//用来区分本地联系人和黄页号码,0 表示本地联系人； 1 表示黄页号码

	String fieldName() default "";

	enum FieldType{
		VARCHAR,INT,REAL
	}
}