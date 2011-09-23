package com.tectria.converterous;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class ConvertTool {
	private String toUnit;
	private String fromUnit;
	private float toNum;
	private float fromNum;
	public ConvertTool(String toUnit, String fromUnit, Float fromNum) {
		this.toUnit = toUnit;
		this.fromUnit = fromUnit;
		this.toNum = 0.0f;
		this.fromNum = fromNum;
	}
	
	public ConvertTool() {
		this.toUnit = null;
		this.fromUnit = null;
		this.toNum = 0.0f;
		this.fromNum = 0.0f;
	}
	
	public float convert() {
		String name = this.fromUnit + "to" + this.toUnit;
		try {
			//Method method = this.getClass().getMethod(name, Float.class);
			Class cla = Class.forName("com.tectria.converterous.ConvertTool");
			Class partypes[] = new Class[1]; 
			partypes[0] = Float.TYPE;
			Method method = cla.getMethod(name, partypes);
			ConvertTool invoke = this;
			Object arg[] = new Object[1];
			arg[0] = this.fromNum;
			Object object = method.invoke(invoke, arg);
			this.toNum = ((Float)object).floatValue();
			//this.toNum = ((Float)method.invoke(fromNum)).floatValue();
			return this.toNum;
		} catch (NoSuchMethodException e) {
			return 0.0f;
		} catch (SecurityException e) {
			return 0.0f;
		} catch (IllegalAccessException e) {
			return 0.0f;
		} catch (IllegalArgumentException e) {
			return 0.0f;
		} catch (InvocationTargetException e) {
			return 0.0f;
		} catch (ClassNotFoundException e) {
			return 0.0f;
		}
	}
	
	public void setFromNum(float n) {
		this.fromNum = n;
	}
	
	public void setFromUnit(String u) {
		this.fromUnit = u;
	}
	
	public void setToUnit(String u) {
		this.toUnit = u;
	}
	
	public void swapUnits() {
		String tmp = this.toUnit;
		this.toUnit = this.fromUnit;
		this.fromUnit = tmp;
	}
	
	/*
	 * MASS
	 */
	
	/*
	 * VOLUME
	 */
	
	//Pints to quarts
	public Float pttoqt(float i) {
		return i/2f;
	}
	
	//Pints to gallons
	public float pttogal(float i) {
		return i/8f;
	}
	
	//Quarts to pints
	public float qttopt(float i) {
		return i*2f;
	}
	
	//Quarts to gallons
	public float qttogal(float i) {
		return i/4f;
	}

	//Gallons to quarts
	public float galtoqt(float i) {
		return i*4f;
	}
	
	//Gallons to pints
	public float galtopt(float i) {
		return i*8f;
	}
	
	/*
	 * DISTANCE
	 */
	
	// Inches to Feet
	public float intoft(float i) {
		return i/12f;
	}
	
	// Feet to Inches
	public float fttoin(float i) {
		return i*12f;
	}
	
	// Centimeters to Inches
	public float cmtoin(float i) {
		return i*0.3937008f;
	}
	
	// Inches to Centimeters
	public float intocm(float i) {
		return i/0.3937008f;
	}
	
	// Centimeters to Feet
	public float cmtoft(float i) {
		return i/30.48f;
	}
	
	// Feet to Centimeters
	public float fttocm(float i) {
		return i*30.48f;
	}
	
	// Inches to Millimeters
	public float intomm(float i) {
		return i*25.4f;
	}
	
	// Millimeters to Inches
	public float mmtoin(float i) {
		return i/25.4f;
	}
	
	// Millimeters to Centimeters
	public float mmtocm(float i) {
		return i/10f;
	}
	
	// Centimeters to Millimeters
	public float cmtomm(float i) {
		return i*10f;
	}
	
	// Millimeters to Feet
	public float mmtoft(float i) {
		return i/304.8f;
	}
	
	// Feet to Millimeters
	public float fttommm(float i) {
		return i*304.8f;
	}
	
	// Mile to Feet
	public float mitoft(float i) {
		return i*5280f;
	}
	
	// Feet to Mile
	public float fttomi(float i) {
		return i/5280f;
	}
	
	// Miles to Inches
	public float mitoin(float i) {
		return i*63360f;
	}
	
	// Inches to Miles
	public float intomi(float i) {
		return i/63360f;
	}
	
	// Miles to Centimeters
	public float mitocm(float i) {
		return i*160934.4f;
	}
	
	// Miles to Millimeters
	public float mitomm(float i) {
		return i*1609344f;
	}
	
	// Millimeters to Miles
	public float mmtomi(float i) {
		return i/1609344f;
	}
	
	// Meters to Centimeters
	public float mtocm(float i) {
		return i*100f;
	}
	
	// Centimeters to Meters
	public float cmtom(float i) {
		return i/100f;
	}
	
	// Meters to Feet
	public float mtoft(float i) {
		return i*3.280839895f;
	}
	
	// Feet to Meters
	public float fttom(float i) {
		return i/3.280839895f;
	}
	
	// Meters to Inches
	public float mtoin(float i) {
		return i*39.37007874f;
	}
	
	// Inches to Meters
	public float intom(float i) {
		return i/39.37007874f;
	}

	// Meters to Miles
	public float mtomi(float i) {
		return i/1609.344f;
	}
	
	// Miles to Meters
	public float mitom(float i) {
		return i*1609.344f;
	}
	
	// Meters to Millimeters
	public float mtomm(float i) {
		return i*1000f;
	}
	
	// Millimeters to meters
	public float mmtom(float i) {
		return i/1000f;
	}
	
	// Kilometers to Centimeters
	public float kmtocm(float i) {
		return i*100000f;
	}
	
	// Centimeters to kilometers
	public float cmtokm(float i) {
		return i/100000f;
	}
	
	// Kilometers to Feet
	public float kmtoft(float i) {
		return i*3280.839895f;
	}
	
	// Feet to Kilometers
	public float fttokm(float i) {
		return i/3280.839895f;
	}
	
	// Kilometers to Inches
	public float kmtoin(float i) {
		return i*39370.07874f;
	}

	// Inches to Kilometers
	public float intokm(float i) {
		return i/39370.07874f;
	}
	
	// Kilometers to Meters
	public float kmtom(float i) {
		return i*1000f;
	}
	
	// Meters to Kilometers
	public float mtokm(float i) {
		return i/1000f;
	}

	// Kilometers to Miles
	public float kmtomi(float i) {
		return i/1.609344f;
	}
	
	// Miles to Kilometers
	public float mitokm(float i) {
		return i*1.609344f;
	}
	
	// Kilometers to Millimeters
	public float kmtomm(float i) {
		return i*1000000f;
	}
	
	// Millimeters to kilometers
	public float mmtokm(float i) {
		return i/1000000f;
	}

	// Yards to Centimeters
	public float ydtocm(float i) {
		return i*91.44f;
	}
	
	// Centimeters to Yards
	public float cmtoyd(float i) {
		return i/91.44f;
	}
	
	// Yards to Feet
	public float ydtoft(float i) {
		return i*3f;
	}
	
	// Feet to yards
	public float fttoyd(float i) {
		return i/3f;
	}
	
	// Yards to Inches
	public float ydtoin(float i) {
		return i*36f;
	}
	
	// Inches to Yards
	public float intoyd(float i) {
		return i/36f;
	}
	
	// Yards to Kilometers
	public float ydtokm(float i) {
		return i/1093.6132983f;
	}
	
	// Kilometers to Yards
	public float kmtoyd(float i) {
		return i*1093.6132983f;
	}
	
	// Yards to Meters
	public float ydtom(float i) {
		return i/1.0936132983f;
	}
	
	// Meters to Yards
	public float mtoyd(float i) {
		return i*1.0936132983f;
	}

	// Yards to Miles
	public float ydtomi(float i) {
		return i/1760f;
	}
	
	// Miles to Yards
	public float mitoyd(float i) {
		return i*1760f;
	}

	// Yards to Millimeters
	public float ydtomm(float i) {
		return i*914.4f;
	}
	
	// Millimeters to yards
	public float mmtoyd(float i) {
		return i/914.4f;
	}
	
	/*
	 * TIME
	 */
	
	//Seconds to hours
	public float stoh(float i) {
		return i/3600f;
	}
	
	//Seconds to minutes
	public float stomin(float i) {
		return i/60f;
	}
	
	//Minutes to seconds
	public float mintos(float i) {
		return i*60f;
	}
		
	//Minutes to hours
	public float mintoh(float i) {
		return i/60f;
	}
	
	//Hours to seconds
	public float htos(float i) {
		return i*3600f;
	}
	
	//Hours to minutes
	public float htomin(float i) {
		return i*60f;
	}
	
}
