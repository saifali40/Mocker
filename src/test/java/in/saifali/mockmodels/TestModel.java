package in.saifali.mockmodels;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestModel{

	private String string;

	private String nullstrign = null;

	private String stringwithValue = "Active";

	private String stringbooleanValue = "True";

	Set<String> setwithouvalue;
	HashSet<String> hashsetnoValue;

	HashSet<String> setwithoutvalueintialized = new HashSet<>();

	Set<String> setWithvalueintialized = getSet();

	Set<Integer> setWithvalueintializedinteger = getSetInteger();

	Map<String, Boolean> demo;

	private Integer novalueint;

	private Integer valueint = 1;

	private Boolean novaluebool;

	private Boolean valueBool = true;

	private Date nodate;

	private Date date = new Date(System.currentTimeMillis());

	private Set<String> getSet() {
		Set<String> retVal = new HashSet<>();
		retVal.add("A");
		retVal.add("B");
		return retVal;
	}
	private Set<Integer> getSetInteger() {
		Set<Integer> retVal = new HashSet<>();
		retVal.add(1);
		retVal.add(2);
		return retVal;
	}

}
	
