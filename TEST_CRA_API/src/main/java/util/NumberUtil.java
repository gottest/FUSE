package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtil {
	
	private static final String[] NUMBER_SCALES = {
        "ล้าน", "สิบ", "ร้อย",
        "พัน", "หมื่น", "แสน", ""
    };
    private static final String[] DIGIT_WORDS = {
        "ศูนย์", "หนึ่ง", "สอง",
        "สาม", "สี่", "ห้า", "หก",
        "เจ็ด", "แปด", "เก้า"
    };
    
	public static String format(Number n){
		return new DecimalFormat("#,##0.00").format(n); 
	}
	
	public static String append2Digits(String strAmount){
		StringBuffer sb = null;
		
		sb = new StringBuffer(strAmount);
		sb.insert(strAmount.length()-2, '.');
		
		return sb.toString();
	}

	public static String remove2Digits(double amount){
		String s = Double.toString(amount);
		StringBuffer sb = new StringBuffer(s);
		
		s = s.substring(s.indexOf(".")+1, s.length());

		sb.deleteCharAt(sb.indexOf("."));
		if(s.length() == 1){
			sb.append("0");
		}	
		
		return sb.toString();
	}
	
	public static String padLeft10Digits(String strAmount) throws Exception{
		if(strAmount.length() > 11){
			throw new Exception("Amount value is over than 11 digits.");
		}
		
		StringBuffer sb = null;
		sb = new StringBuffer(strAmount);
		
		for(int i=strAmount.length(); i<10; i++){
			sb.insert(0, '0');
		}
		
		return sb.toString();
	}

	public static String convertTo11Digits(Double amount) throws Exception{
		DecimalFormat intFormat = new DecimalFormat();
        intFormat.setDecimalSeparatorAlwaysShown(true);
        intFormat.setGroupingUsed(false);
        intFormat.setMinimumFractionDigits(2);
        intFormat.setMaximumFractionDigits(2);
        String doubleStr = intFormat.format(amount.doubleValue());
        
        String fraction = doubleStr.substring(doubleStr.indexOf(".")+1, doubleStr.length());
        String integer = doubleStr.substring(0, doubleStr.indexOf("."));
//        System.out.println(" doubleStr >>>>>>>>> "+ doubleStr);
//        System.out.println(" fraction >>>>>>>>> "+ fraction);
//        System.out.println(" integer >>>>>>>>> "+ integer);
        
//		if(integer.length() > 11){
//			throw new Exception("Amount value is over than 11 digits.");
//		}
		
		StringBuffer sb = null;
		sb = new StringBuffer(integer);
		
		for(int i=integer.length(); i<9; i++){
			sb.insert(0, '0');
		}
		
		return sb.toString() + fraction;
	}	
	
	public static String convertTo2DigitsAmount(String data){
		if(data.indexOf(".") != -1){
			StringBuffer sb = new StringBuffer(data);
			data = sb.deleteCharAt(data.indexOf(".")).toString();
		}else{
			data += "00";
		}
		return data;
	}
	
	public static String convertHexStrToInt(String hex){
		String s = null;
		try {
			int value = Integer.parseInt(hex, 16);
			s = Integer.toString(value);
			s = StringUtil.lpad(s, "0", 3);
			//System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
			s = hex;
		}
		return s;
	}
	
	public static String getBahtText(String number) {
	   	BigDecimal amount = new BigDecimal(number);
	   	String bahtText = getBahtTextFormat(amount);
		return bahtText;
	}
	    
	public static String getUSDText(String number) {
	   	BigDecimal amount = new BigDecimal(number);
	   	String usdText = getUSDTextFormat(amount);
		return usdText;
	}
	
	public static String getBahtText(Number number) {
	   	BigDecimal amount = new BigDecimal(String.valueOf(number));
	   	String bahtText = getBahtTextFormat(amount);
	   	return bahtText;
	}
	
	private static String getBahtTextFormat(BigDecimal amount)
    {
        StringBuffer buffer = new StringBuffer();
        BigDecimal absolute = amount.abs();
        int precision = absolute.precision();
        int scale = absolute.scale();
        int rounded_precision = ((precision - scale) + 2);
        MathContext mc = new MathContext(rounded_precision, RoundingMode.HALF_UP);
        BigDecimal rounded = absolute.round(mc);
        BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
        boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));

        compound[0] = compound[0].setScale(0);
        compound[1] = compound[1].movePointRight(2);

        if (negative_amount)
        {
            buffer.append("สิบ");
        }

        buffer.append(getNumberTextFormat(compound[0].toBigIntegerExact()));
        buffer.append("บาท");

        if (0 == compound[1].compareTo(BigDecimal.ZERO))
        {
            buffer.append("ถ้วน");
        }
        else
        {
            buffer.append(getNumberTextFormat(compound[1].toBigIntegerExact()));
            buffer.append("สตางค์");
        }

        return buffer.toString();
    }
	
	private static String getUSDTextFormat(BigDecimal amount)
    {
        StringBuffer buffer = new StringBuffer();
        BigDecimal absolute = amount.abs();
        int precision = absolute.precision();
        int scale = absolute.scale();
        int rounded_precision = ((precision - scale) + 2);
        MathContext mc = new MathContext(rounded_precision, RoundingMode.HALF_UP);
        BigDecimal rounded = absolute.round(mc);
        BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
        boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));

        compound[0] = compound[0].setScale(0);
        compound[1] = compound[1].movePointRight(2);

        if (negative_amount)
        {
            buffer.append("สิบ");
        }

        buffer.append(getNumberTextFormat(compound[0].toBigIntegerExact()));
        buffer.append("ดอลลาร์");

        if (0 == compound[1].compareTo(BigDecimal.ZERO))
        {
            buffer.append("ถ้วน");
        }
        else
        {
            buffer.append(getNumberTextFormat(compound[1].toBigIntegerExact()));
            buffer.append("เซนต์");
        }

        return buffer.toString();
    }

    private static String getNumberTextFormat(BigInteger number)
    {
        StringBuffer buffer = new StringBuffer();
        char[] digits = number.toString().toCharArray();

        for (int index = digits.length; index > 0; --index)
        {
            int digit = Integer.parseInt(String.valueOf(digits[digits.length - index]));
            String digit_text = DIGIT_WORDS[digit];
            int scale_idx = ((1 < index)? ((index - 1) % 6) : 6);

            if ((1 == scale_idx) && (2 == digit))
            {
                digit_text = "ยี่";
            }

            if (1 == digit)
            {
                switch (scale_idx)
                {
	                case 0	:	
	                case 6	:	buffer.append((index < digits.length) ? "เอ็ด" : digit_text); break;
	                case 1	: 	break;
	                default	:	buffer.append(digit_text);break;
                }
            }
            else if (0 == digit)
            {
                if (0 == scale_idx)
                {
                    buffer.append(NUMBER_SCALES[scale_idx]);
                }
                continue;
            }
            else
            {
                buffer.append(digit_text);
            }

            buffer.append(NUMBER_SCALES[scale_idx]);
        }
        
        if(number == BigInteger.valueOf(0)){
        	buffer.append(DIGIT_WORDS[0]);
    	}
        
        return buffer.toString();
    }
    
    public static String convertToThaiNumber(int i){
		String[] thaiNumber =  {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า"};
		return thaiNumber[i];
	}
	
    public static String convertAlrabicToThaiNumber(Double amount){
    	if(amount == null) return ""; 
    	return convertAlrabicToThaiNumber(String.valueOf(amount));
    }
    public static String convertAlrabicToThaiNumber(Integer amount){
    	if(amount == null) return ""; 
    	return convertAlrabicToThaiNumber(String.valueOf(amount));
    }
    public static String convertAlrabicToThaiNumber(Long amount){
    	if(amount == null) return ""; 
    	return convertAlrabicToThaiNumber(String.valueOf(amount));
    }
	public static String convertAlrabicToThaiNumber(String str){
		if(str == null || str.isEmpty()) return ""; 
		str = str.replaceAll("0", "๐");
		str = str.replaceAll("1", "๑");
		str = str.replaceAll("2", "๒");
		str = str.replaceAll("3", "๓");
		str = str.replaceAll("4", "๔");
		str = str.replaceAll("5", "๕");
		str = str.replaceAll("6", "๖");
		str = str.replaceAll("7", "๗");
		str = str.replaceAll("8", "๘");
		str = str.replaceAll("9", "๙");
		return str;
	}
    
    private static final String[] SCALE_BIG_EN = { "thousand", "million", "billion" };
    private static final String[] SCALE_BIG_EN2 = { "Thousand", "Million", "Billion" };
    private static final String[] SCALE_TEN_EN = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
    private static final String[] SCALE_TEN_EN2 = { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private static final String[] DIGIT_EN = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
    private static final String[] DIGIT_EN2 = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private static final String[] SYMBOLS_EN = { "minus", "dollars", "cents", "and", ",", " ", "$" };
    //private static final String[] SYMBOLS_EN2 = { "Minus", "Baht", "Satang", "and", ",", " ", "$" };
    private static final String[] SYMBOLS_EN2 = { "Minus", "Dollars", "Cents", "and", ",", " ", "$" };
    private static final String[] SYMBOLS_EN3 = { "Minus", "Baht", "Satang", "and", ",", " ", "$" };
    private String valueText;
    
    private static String getThaiBaht(BigDecimal amount, int caseType) {
    	StringBuilder builder = new StringBuilder();
    	BigDecimal absolute = amount.abs();
    	int precision = absolute.precision();
    	int scale = absolute.scale();
    	int rounded_precision = ((precision - scale) + 2);
    	MathContext mc = new MathContext(rounded_precision,
    			RoundingMode.HALF_UP);
    	BigDecimal rounded = absolute.round(mc);
    	BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
    	boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));
    	
    	compound[0] = compound[0].setScale(0);
    	compound[1] = compound[1].movePointRight(2);
    	
    	if (negative_amount) {
    		if(caseType == 2){
    			builder.append(SYMBOLS_EN2[0].toString() + " ");
    		}
    		else{
    			builder.append(SYMBOLS_EN[0].toString() + " ");
    		}
    		
    	}
    	
    	builder.append(getNumberText(compound[0].toBigIntegerExact(), caseType));
    	if(caseType == 2){
    		builder.append(SYMBOLS_EN2[1].toString());
    	}
    	else{
    		builder.append(SYMBOLS_EN[1].toString());
    	}
    	
    	
    	if (0 == compound[1].compareTo(BigDecimal.ZERO)) {
    		if(caseType == 2){
    			builder.append(" Only ");
    		}
    		else{
    			builder.append("");
    		}
    		
    	} else {
    		if(caseType == 2){
    			builder.append(" " + SYMBOLS_EN2[3].toString() + " ");
        		builder.append(getNumberText(compound[1].toBigIntegerExact(), caseType));
        		builder.append(" " + SYMBOLS_EN2[2].toString());
    		}
    		else{
    			builder.append(" " + SYMBOLS_EN[3].toString() + " ");
        		builder.append(getNumberText(compound[1].toBigIntegerExact(), caseType));
        		builder.append(" " + SYMBOLS_EN[2].toString());
    		}
    	}
    	
    	return builder.toString();
    }
    
    private static String getThaiUSDTransliterationToBaht(BigDecimal amount, int caseType) {
    	StringBuilder builder = new StringBuilder();
    	BigDecimal absolute = amount.abs();
    	int precision = absolute.precision();
    	int scale = absolute.scale();
    	int rounded_precision = ((precision - scale) + 2);
    	MathContext mc = new MathContext(rounded_precision,
    			RoundingMode.HALF_UP);
    	BigDecimal rounded = absolute.round(mc);
    	BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
    	boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));
    	
    	compound[0] = compound[0].setScale(0);
    	compound[1] = compound[1].movePointRight(2);
    	
    	if (negative_amount) {
    		if(caseType == 2){
    			builder.append(SYMBOLS_EN3[0].toString() + " ");
    		}
    		else{
    			builder.append(SYMBOLS_EN3[0].toString() + " ");
    		}
    		
    	}
    	
    	builder.append(getNumberText(compound[0].toBigIntegerExact(), caseType).trim());
    	if(caseType == 2){
    		builder.append(" "+SYMBOLS_EN3[1].toString());
    	}
    	else{
    		builder.append(SYMBOLS_EN[1].toString());
    	}
    	
    	
    	if (0 == compound[1].compareTo(BigDecimal.ZERO)) {
    		if(caseType == 2){
    			builder.append(" Only");
    		}
    		else{
    			builder.append("");
    		}
    		
    	} else {
    		if(caseType == 2){
    			builder.append(" " + SYMBOLS_EN3[3].toString() + " ");
    			builder.append(getNumberText(compound[1].toBigIntegerExact(), caseType));
    			builder.append(" " + SYMBOLS_EN3[2].toString());
    		}
    		else{
    			builder.append(" " + SYMBOLS_EN[3].toString() + " ");
    			builder.append(getNumberText(compound[1].toBigIntegerExact(), caseType));
    			builder.append(" " + SYMBOLS_EN[2].toString());
    		}
    	}
    	
    	return builder.toString();
    }
    
    private static String getNumberText(BigInteger number, int caseType) {
        StringBuffer buffer = new StringBuffer();
        if (number.compareTo(new BigInteger("999")) <= 0) {
            return convertLessThanThousand(number.intValue(), caseType);
        }
        int t = 0;
        while (number.compareTo(new BigInteger("0")) > 0) {
            if (number.intValue() % 1000 != 0) {
                StringBuffer buffer2 = new StringBuffer(convertLessThanThousand(number.intValue() % 1000, caseType));
                if (t > 0) {
                	if(caseType == 2){
                		 buffer2 = buffer2.append(" " + SCALE_BIG_EN2[t - 1]);
                	}
                	else{
                		 buffer2 = buffer2.append(" " + SCALE_BIG_EN[t - 1]);
                	}
                }
                if (buffer == null) {
                    buffer = buffer2;
                } else {
                    buffer = buffer2.append(" " + buffer);
                }
            }
            number = number.divide(new BigInteger("1000"));
            t++;
        }
        return buffer.toString();
    }
    
    private static String convertLessThanThousand(int number, int caseType) {
        String digit_string = ""; 
        if(caseType == 2){
        	digit_string = DIGIT_EN2[number / 100] + " hundred";
        }
        else{
        	digit_string = DIGIT_EN[number / 100] + " hundred";
        }
        String convert_string = convertLessThanHundred(number % 100, caseType);
        if (number <= 99) {
            return convert_string;
        } else if (number % 100 == 0) {
            return digit_string;
        } else {
            return digit_string + " " + convert_string;
        }
    }
 
    private static String convertLessThanHundred(int number, int caseType) {
        if (number < 20) {
        	if(caseType == 2){
        		 return DIGIT_EN2[number];
        	}
        	else{
        		 return DIGIT_EN[number];
        	}
           
        }
        String ten_string = "";
        	if(caseType == 2){
        		ten_string = SCALE_TEN_EN2[number / 10 - 2];
        	}
        	else{
        		ten_string = SCALE_TEN_EN[number / 10 - 2];
        	}
        		
        if (number % 10 == 0) {
            return ten_string;
        }
        if(caseType == 2){
        	return ten_string + " " + DIGIT_EN2[number % 10];
        }
        else{
        	return ten_string + "-" + DIGIT_EN[number % 10];
        }
        
    }
    
    public String getText(String amount) {
        for (String element : SYMBOLS_EN) {
            amount = amount.replace(element, "");
        }
 
        BigDecimal value = new BigDecimal(amount.trim());
        this.valueText = getThaiBaht(value, 1);
        return this.valueText;
    }
    
    public String getTextUSD(String amount) {
    	for (String element : SYMBOLS_EN2) {
    		amount = amount.replace(element, "");
    	}
    	
    	BigDecimal value = new BigDecimal(amount.trim());
    	this.valueText = getThaiBaht(value, 2);
    	return this.valueText;
    }
    
    public String getTextUSDTransliterationToBaht(String amount) {
    	for (String element : SYMBOLS_EN3) {
    		amount = amount.replace(element, "");
    	}
    	
    	BigDecimal value = new BigDecimal(amount.trim());
    	this.valueText = getThaiUSDTransliterationToBaht(value, 2);
    	return this.valueText;
    }
    
	public static void main(String[] args) throws Exception {
//		String strAmount = "6550000000";
//		System.out.println(strAmount);
//		System.out.println(padLeft10Digits(strAmount));
//		
//		System.out.println("1234567891.51 = "+NumberUtil.getBahtText("1234567891.51"));
//		System.out.println("1234567891.51 = "+NumberUtil.getBahtText(1234567891.51));
//		System.out.println("0 = "+NumberUtil.getBahtText(0));
//		System.out.println("1000000000 = "+NumberUtil.getBahtText(1000000000));
		
//		System.out.println("0.21 "+NumberUtil.getBahtText(String.valueOf(0.21)));
//		System.out.println("0.21 "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(0.21)));
//		System.out.println("-------------------------------------------------");
//		System.out.println("1234567891.51 "+NumberUtil.getBahtText(String.valueOf(1234567891.51)));
//		System.out.println("1234567891.51 "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(1234567891.51)));
//		System.out.println("-------------------------------------------------");
		
		Double sumPriceTHB = 9D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 90D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 11D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 20D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 21D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 900D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 1050D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
		sumPriceTHB = 123456.98D;
		System.out.println("ใบเสร็จ สกุลเงิน THB : "+sumPriceTHB);
		System.out.println(" => ตัวอักษร  : "+NumberUtil.getBahtText(String.valueOf(sumPriceTHB)));
		System.out.println(" => ตัวอักษร  : "+new NumberUtil().getTextUSDTransliterationToBaht(String.valueOf(sumPriceTHB)));
		System.out.println("----------------");
		
	}
}
