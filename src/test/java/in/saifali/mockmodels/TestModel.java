package in.saifali.mockmodels;

import java.util.*;

public class TestModel{

	private String string;

	private String nullstrign = null;

	private String stringwithValue = "Active";

	private String stringbooleanValue = "True";

	Set<String> setwithouvalue;
	Set<String> hashsetnoValue;
	Set<String> setwithoutvalueintialized = new HashSet<>();
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

	Map<Boolean, Boolean> boolMap;
	Map<String, String> strMap;
	Map<Integer, Integer> intMap;
	Map<Long, Long> longMap;

	Set<Boolean> boolSet;
	Set<String> strSet;
	Set<Integer> intSet;
	Set<Long> longSet;

	List<Boolean> boolList;
	List<String> strList;
	List<Integer> intList;
	List<Long> longList;

}
	
