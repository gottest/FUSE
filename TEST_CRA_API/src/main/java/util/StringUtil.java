package util;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtil {

    private static final Logger log = LogManager.getLogger(StringUtil.class);
	
    private static int LPAD = 0;
    private static int RPAD = 1;
    
    private StringUtil() {
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isNumberEmpty(String s) {
        return s == null || s.trim().equals("") || s.trim().equals("0");
    }

    public static String toString(String str) {
        return (str == null ? "" : str.trim());
    }

    public static String toString(String str, String def) {
        return (str == null ? toString(def) : str.trim());
    }

    public static String toSqlString(String str) {
        return toString(str).replaceAll("'", "''");
    }

    public static int toInt(String str) {
        int result = 0;
        try {
            result = Integer.parseInt(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            log.error("not Integer " + str);
        }
        return result;
    }

    public static int toInt(String str, int def) {
        int result = def;
        try {
            result = Integer.parseInt(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            log.error("not Integer " + str);
        }
        return result;
    }

    public static int toInt(Integer i) {
        int result = 0;
        try {
            result = i;
        } catch (Throwable t) {
            log.error("not Integer " + i);
        }
        return result;
    }

    public static long toLong(String str) {
        long result = 0;
        try {
            result = Long.parseLong(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            log.error("not long " + str);
        }
        return result;
    }

    public static long toLong(String str, long def) {
        long result = def;
        try {
            result = Long.parseLong(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            log.error("not long " + str);
        }
        return result;
    }

    public static long toLong(Long l) {
        long result = 0;
        try {
            result = l;
        } catch (Throwable t) {
            log.error("not long " + l);
        }
        return result;
    }

    public static double toDouble(String str) {
        double result = 0;
        try {
        	result = Double.parseDouble(toString(str).replaceAll(",", ""));
        }  catch (Throwable t) {
            //log.error("not double " + str);
        }
        return result;
    }

    public static double toDouble(String str, double def) {
        double result;
        try {
            result = Double.parseDouble(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            result = def;
        }
        return result;
    }

    public static double toDouble(Integer d) {
        double result;
        try {
            result = d;
        } catch (Throwable t) {
            result = 0;
        }
        return result;
    }

    public static double toDouble(Double d) {
        double result;
        try {
            result = d;
        } catch (Throwable t) {
            result = 0d;
        }
        return result;
    }

    public static boolean toBoolean(Boolean b) {
        boolean result = false;
        try {
            result = b;
        } catch (Throwable t) {
            log.error("not boolean " + b);
        }
        return result;
    }

    public static Byte toByte(String str) {
        Byte result = 0;
        try {
            result = Byte.parseByte(toString(str).replaceAll(",", ""));
        } catch (Throwable t) {
            log.error("not byte " + str);
        }
        return result;
    }

    public static String parseString(Object[] objs, int index) {
        if (objs == null || objs.length <= index) {
            return null;
        }
        return parseString(objs[index]);
    }

    public static String parseString(Object obj) {
        if (obj == null || obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public static Integer parseInteger(Object[] objs, int index) {
        if (objs == null || objs.length <= index) {
            return null;
        }
        return parseInteger(objs[index]);
    }

    public static Integer parseInteger(Object obj) {
        if (obj == null || obj instanceof Integer) {
            return (Integer) obj;
        }
        return toInt(obj.toString());
    }

    public static Long parseLong(Object[] objs, int index) {
        if (objs == null || objs.length <= index) {
            return null;
        }
        return parseLong(objs[index]);
    }

    public static Long parseLong(Object obj) {
        if (obj == null || obj instanceof Long) {
            return (Long) obj;
        }
        return toLong(obj.toString());
    }

    public static Double parseDouble(Object[] objs, int index) {
        if (objs == null || objs.length <= index) {
            return null;
        }
        return parseDouble(objs[index]);
    }

    public static Double parseDouble(Object obj) {
        if (obj == null || obj instanceof Double) {
            return (Double) obj;
        }
        return toDouble(obj.toString());
    }

    public static Date parseDate(Object[] objs, int index) {
        if (objs == null || objs.length <= index) {
            return null;
        }
        return parseDate(objs[index]);
    }

    public static Date parseDate(Object obj) {
        if (obj == null || obj instanceof Date) {
            return (Date) obj;
        }
        return DateUtil.createDate(obj.toString(), false);
    }

    public static Byte parseByte(Object obj) {
        if (obj == null || obj instanceof Byte) {
            return (Byte) obj;
        }
        return toByte(obj.toString());
    }

    public static String[] toArrayString(String str, String concat) {
        StringTokenizer token = new StringTokenizer(str, concat);
        String[] result = new String[token.countTokens()];
        for (int i = 0; i < result.length; i++) {
            result[i] = token.nextToken();
        }
        return result;
    }

    public static Hashtable arrayToHashtable(String[] array) {
        Hashtable h = new Hashtable();
        for (int i = 0; array != null && i < array.length; i++) {
            if (!isEmpty(array[i])) {
                h.put(toString(array[i]), "");
            }
        }
        return h;
    }

    public static String arrayToString(String[] array) {
        Hashtable h = arrayToHashtable(array);
        if (h.isEmpty()) {
            return null;
        } else {
            String result = " (";
            int index = 0;
            Enumeration enu = h.keys();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (index == h.size() - 1) {
                    result += "'" + key + "'";
                } else {
                    result += "'" + key + "', ";
                }
                index++;
            }
            result += ") ";
            return result;
        }
    }

    public static String[] intersectArray(String[] str1, String[] str2) {
        Hashtable h1 = arrayToHashtable(str1);
        Hashtable h2 = arrayToHashtable(str2);

        List list = new ArrayList();
        Enumeration enu = h1.keys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            if (h2.containsKey(key)) {
                list.add(key);
            }
        }

        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = (String) list.get(i);
        }
        return result;
    }

    public static String[] unionArray(String[] str1, String[] str2) {
        Hashtable h1 = arrayToHashtable(str1);
        Hashtable h2 = arrayToHashtable(str2);

        Enumeration enu = h1.keys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            h2.put(key, "");
        }

        List list = new ArrayList();
        enu = h2.keys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            list.add(key);
        }

        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = (String) list.get(i);
        }
        return result;
    }

    public static boolean isInArray(String[] str1, String[] str2) {
        Hashtable h1 = arrayToHashtable(str1);
        Hashtable h2 = arrayToHashtable(str2);

        Enumeration enu = h1.keys();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            if (h2.containsKey(key) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInArray(String str1, String[] str2) {
        return isInArray(new String[]{str1}, str2);
    }

    // Convert such example HELLO_WORLD -> Hello World
    public static String charCap(String s) {
        StringBuffer bf = new StringBuffer();
        String str[] = s.split("_");
        for (int i = 0; i < str.length; i++) {
            bf.append(upperFirstChar(str[i].toLowerCase())).append(" ");
        }

        return bf.toString();
    }

    public static String lowerFirstChar(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
    }

    public static String upperFirstChar(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
    }

    public static String unescapeHTML(String text) {
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&gt;", ">");
        return text;
    }

    public static String stringReplaceAll(String newStatement, String newChangeFrom,
                                   String newChangeTo) {
        if (newStatement == null)
            return "";
        int index = newStatement.indexOf(newChangeFrom);
        while (index != -1) {
            try {
                String newStatementBf = newStatement.substring(0, index)
                        + newChangeTo
                        + newStatement.substring((index + newChangeFrom
                        .length()), newStatement.length());
                newStatement = newStatementBf;
                index = newStatement.indexOf(newChangeFrom, index
                        + newChangeTo.length());
            } catch (Exception e) {
                index = -1;
                log.debug("Error :replaceStringAll" + e.getMessage());
                log.error(e);
            }
        }
        return newStatement;
    }

    public static List<String> toList(String inputString, String splitString) {
        List<String> resultList = new ArrayList<String>();
        if (inputString != null && !inputString.equals("")) {
            StringTokenizer st = new StringTokenizer(inputString, splitString);
            while (st.hasMoreTokens()) {
                // log.debug("st = "+st.nextToken().trim());
                resultList.add(st.nextToken());
            }
        }
        return resultList;
    }

    public static List<Integer> toListOfInteger(String inputString, String splitString) {
        List<Integer> resultList = new ArrayList<Integer>();
        if (inputString != null && !inputString.equals("")) {
            // inputString.indexOf("&&");
            // inputString.lastIndexOf("&&");
            StringTokenizer st = new StringTokenizer(inputString, splitString);
            while (st.hasMoreTokens()) {
                // log.debug("st = "+st.nextToken());
                resultList.add(new Integer(st.nextToken().trim()));
            }
        }
        return resultList;
    }

    public static Set<String> toSet(String inputString, String splitString) {
        Set<String> resultSet = new HashSet<String>();
        if (inputString != null && !inputString.equals("")) {
            StringTokenizer st = new StringTokenizer(inputString, splitString);
            while (st.hasMoreTokens()) {
                resultSet.add(st.nextToken());
            }
        }
        return resultSet;
    }

    public static String stringRemoveline(String inputString) {
        if (inputString == null)
            return "";
        String outputString = inputString.replace('\13', ' ').replace('\10',
                ' ').replace('\n', ' ').replace('\t', ' ');
        return outputString;
    }

    public static String trimString(String string) {
        if (string == null)
            return "";
        string = string.trim();
        return string;
    }

    private static String padding(String str, String pad, int length, int side) throws Exception {
        StringBuffer padstr = null;
        
        padstr = new StringBuffer();
        for( int i=0; i<(length - str.length()); i++ )
            padstr.append(pad);
        
        if( side == 0 )
            return padstr.toString() + str;
        else if( side == 1 )
            return str + padstr.toString();
        else
            throw new Exception("Invarid side");
     }
     
     public static String lpad(String str, String pad, int length) throws Exception {
        return padding(str, pad, length, LPAD);
     }
     
     public static String rpad(String str, String pad, int length) throws Exception {
        return padding(str, pad, length, RPAD);
     }
     
 	public static boolean isFoundinArry(String arry,String str) {
		if(arry.indexOf(str) > -1) {
			return true;
		}
		return false;
	}
 	
 	public static String toStringWithoutDelimeter(String str, String dim) throws Exception {
 		
 		String retStr = "";
 		String[] arrStr = str.split(dim);
 		
 		for(int i=0; i<arrStr.length; i++){
 			retStr = retStr.concat(arrStr[i]);  
 		}
 		
 		return retStr;
 	}
 	
 	public static String formatEveryEvent(String ddMM, String lang) {
		if (ddMM == null) {
			return "";
		}
		String [] m_months_th = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};
		String [] m_months_en = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		String [] ddMM_arr = ddMM.split("/");
		String dd = new String(ddMM_arr[0]);
		String MM = new String(ddMM_arr[1]);
		
		String str = "";
		
		System.out.println("lang ====>>>>>>>>>> = "+lang);
		
		if("ALL".equals(MM)){
			if("en".equals(lang)){
				str = "day "+Integer.parseInt(dd)+" "+"of every month";
			}
			else{
				str = "วันที่ "+Integer.parseInt(dd)+" "+"ทุกเดือน";
			}
		}
		else{
			if("en".equals(lang)){
				str = "day "+Integer.parseInt(dd)+" of "+m_months_en[Integer.parseInt(MM)];
			}
			else{
				str = "วันที่ "+Integer.parseInt(dd)+" of "+m_months_th[Integer.parseInt(MM)];
			}
			
		}
		return str;
	}
}
