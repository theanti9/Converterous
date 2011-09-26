package com.tectria.converterous;

import java.lang.reflect.Field;
import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;

public class ConvertTool {
	
	private String toUnit;
	private String fromUnit;
	private double fromNum;
	private boolean fromSI;
	private boolean toSI;
	
	public ConvertTool(String toUnit, String fromUnit, double fromNum, boolean fromSI, boolean toSI) {
		this.toUnit = toUnit;
		this.fromUnit = fromUnit;
		this.fromNum = fromNum;
		this.fromSI = fromSI;
		this.toSI = toSI;
	}
	
	public ConvertTool() {
		this.toUnit = null;
		this.fromUnit = null;
		this.fromNum = 0.0;
		this.fromSI = true;
		this.toSI = true;
	}
	
	@SuppressWarnings("rawtypes")
	public double convert() {
		
		String name = "javax.measure.unit.";
		try {
			String fromSi;
			String toSi;
			if (this.fromSI){
				fromSi = "SI";
			} else {
				fromSi = "NonSI";
			}
			if (this.toSI){
				toSi = "SI";
			} else {
				toSi = "NonSI";
			}
			Class fromUnitClass = Class.forName(name.concat(fromSi));
			Class toUnitClass = Class.forName(name.concat(toSi));
			Field fromUnitField;
			Field toUnitField;
			Unit fromUnit;
			Unit toUnit;
			fromUnitField = fromUnitClass.getField(this.fromUnit);
			toUnitField = toUnitClass.getField(this.toUnit);
			fromUnit = (Unit)fromUnitField.get(fromUnitClass);
			toUnit = (Unit)toUnitField.get(toUnitClass);
			
			UnitConverter converterTo = fromUnit.getConverterTo(toUnit);
			Measure m1 = Measure.valueOf(this.fromNum, fromUnit);
			double m2 = m1.doubleValue(fromUnit);
			
			return converterTo.convert(m2);
		} catch (SecurityException e) {
			return 0.0;
		} catch (IllegalAccessException e) {
			return 0.0;
		} catch (IllegalArgumentException e) {
			return 0.0;
		} catch (ClassNotFoundException e) {
			return 0.0;
		} catch (NoSuchFieldException e) {
			return 0.0;
		}
	}
	
	public void setFromSI(boolean b) {
		this.fromSI = b;
	}
	
	public void setToSI(boolean b) {
		this.toSI = b;
	}
	
	public void setFromNum(double n) {
		this.fromNum = n;
	}
	
	public void setFromUnit(String u) {
		this.fromUnit = u.toUpperCase();
	}
	
	public void setToUnit(String u) {
		this.toUnit = u.toUpperCase();
	}
	
	public void swapUnits() {
		String tmp = this.toUnit;
		this.toUnit = this.fromUnit;
		this.fromUnit = tmp;
	}
}