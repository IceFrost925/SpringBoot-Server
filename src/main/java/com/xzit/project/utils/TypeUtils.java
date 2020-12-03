package com.xzit.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 实现Java基本类型之间转换
 */
public class TypeUtils {
	private static Logger logger = LoggerFactory.getLogger(TypeUtils.class);
	/**
	 * 判断对象是否为空,(包括null and "")
	 * @param o 判断对象
	 * @return 为空返回True,不为空返回False
	 */
	public static boolean isEmpty(Object o) {

		return (o == null || o.toString().equals("")) ? true : false;
	}

	public static boolean isEmpty(String s) {

		return s == null || s.equals("");
	}

	public static boolean isEmpty(List<?> list) {

		return (list == null || list.size() <= 0);
	}
	public static boolean isEmpty(Map<?,?> map) {
		return (map == null || map.size() <= 0);
	}

	public static String getString(Object o) {
		return o != null && !o.equals("null") ? o.toString() : "";
	}

	public static String getStringForEdit(Object o) {
		return o != null&&!"".equals(o.toString()) ? o.toString() : "null";
	}

	public static String subString(String s, int index, int length) {
		return s != null ? s.substring(index, length) : "";
	}

	/*public static int getIntx(String s) {
		return getIntx(s, 0);
	}*/

	public static int getIntx(String s, int defaultValue) {
		if (s != null) {
			s = s.replaceAll("[^\\d]", "");
			return getInt(s, defaultValue);
		}
		return defaultValue;
	}
	public static long getLong(String s){
		Long l=0L;
		if(null!=s){
			l=Long.parseLong(s);
		}
		return l;
	}
    //四舍五入
	public static int getInt(String s) {
		int num=0;
		if(s!=null&&!"".equals(s)){
			num=new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		}
		return num;
	}

	public static boolean getBoolean(String s) {

		return Boolean.parseBoolean(s);
	}

	public static int getInt(String s, int defaultValue) {
		int i = 0;
		if (!isEmpty(s)) {
			try {
				i = new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			} catch (Exception e) {
				i = defaultValue;
			}
		} else {
			i = defaultValue;
		}
		return i;
	}

	public static int getInt(Object o, int defaultValue) {
		return getInt(getString(o), defaultValue);
	}

	public static int getInt(Object o) {
		return getInt(TypeUtils.getString(o), 0);
	}

	public static float getFloat(String s, float defaultValue) {
		float i = 0;
		if (!isEmpty(s)) {
			try {
				i = Float.parseFloat(s);
			} catch (NumberFormatException e) {
				i = defaultValue;
			}
		} else {
			i = defaultValue;
		}
		return i;
	}

	public static float getFloat(String s) {
		return getFloat(s, 0);
	}

	public static double getDouble(String s, double defaultValue) {
		double i = 0;
		if (!isEmpty(s)) {
			try {
				i = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				i = defaultValue;
			}
		} else {
			i = defaultValue;
		}
		BigDecimal b = new BigDecimal(Double.toString(i));
	    BigDecimal one = new BigDecimal("1");
		return b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static double getDouble(String s){
		return getDouble(s,0);

	}

	public static float getFloat(Object o, float defaultValue) {
		return getFloat(getString(o), defaultValue);
	}

	public static float getFloat(Object o) {
		return getFloat(getString(o), 0);
	}
	public static double getDouble(Object o) {
		return getDouble(getString(o), 0);
	}
	public static String getDecimal(Object o, String format) {
		DecimalFormat df = new DecimalFormat(format);
		float f = getFloat(o, 0);
		String str = df.format(f);
		if (str.indexOf(".") == 0)
			str = "0" + str;
		return str;
	}

	public static String dcodeUtf8(String str) {
		str = TypeUtils.getString(str);
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static double getMinNum(double[] args) {
		if (args != null & args.length > 0) {
			double max = 0;
			double min = 99999999999L;
			for (double f : args) {
				// 顺序排序按 水位 r
				if (args.length == 1) {
					min = f;
				} else {
					if (f > max) {
						max = f;
					}
					if (f < min) {
						min = f;
					}
				}

			}
			return min;
		}
		return 0;
	}

	public static double getMaxNum(double[] args) {

		if (args != null & args.length > 0) {
			double max = 0;
			double min = 99999999999d;
			for (double f : args) {
				// 顺序排序按 水位 r
				if (args.length == 1) {
					max = f;
				} else {
					if (f > max) {
						max = f;
					}
					if (f < min) {
						min = f;
					}
				}

			}
			return max;
		}
		return 0;
	}

	/**
     * 把文本编码为Html代码
     * @param
     * @return 编码后的字符串
     */
    public static String htmEncode(String str)
    {
    	if(str==null)return "";
    	str = str.replaceAll("&","&amp;");
        str = str.replaceAll("'","''");
        str = str.replaceAll("\"","&quot;");
        str = str.replaceAll(" ","&nbsp;");
        str = str.replaceAll("<","&lt;");
        str = str.replaceAll(">","&gt;");
        str = str.replaceAll("\n","<br/>");
        return str;
    }

    public static String join( Object[] o , String flag ){
        StringBuffer str_buff = new StringBuffer();
        for(int i=0 , len=o.length ; i<len ; i++){
            str_buff.append( String.valueOf( o[i] ) );
            if(i<len-1)str_buff.append( flag );
        }
        return str_buff.toString();
    }

     /**
	 * 数组合拼工具类
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
    /**
	 *  zhu
	 * @param sql
	 * @return
	 */
	public static String transforSql(String sql,Object [] par){
		StringBuffer sqls=new StringBuffer();
		sqls.append(sql.substring(0, sql.indexOf("?")));
		for(int i=0;i<par.length;i++){
			sqls.append("?,");
		}
		sqls=new StringBuffer(sqls.substring(0,sqls.lastIndexOf(",")));
		sqls.append(sql.substring(sql.indexOf("?")+1,sql.length()));
		return sqls.toString();
	}

	/**
	 * 获取上传图片名称
	 * @return
	 */
	public static String getPicName(String st){
		try{
			int index = st.lastIndexOf(".");
	    	String format = st.substring(index);
	    	//按照规则生成新的文件名
	    	String date = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
	    	StringBuffer stringBuffer = new StringBuffer();
	    	stringBuffer.append(date);
	    	Random random = new Random();
	    	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
	                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	                'X', 'Y', 'Z'};
	    	for (int i = 0; i < 4; i++) {
	    		String code = String.valueOf(codeSequence[random.nextInt(26)]);
	    		stringBuffer.append(code);
	    	}
	    	stringBuffer.append(format);
	    	String realName = stringBuffer.toString();
	    	return realName;
		}catch(Exception e){
			logger.error("公共方法获取上传图片名称：",new Object[]{e});
			return "";
		}
	}

	public static int getStrCount(String s,String toFind){
		if(isEmpty(s))
		{
			return 0;
		}
		int count=0;
		while(true){
			int index = s.indexOf(toFind);
			if(index==-1){
				break;
			}else
			{
				count++;
				s=s.substring(index+1);
			}
		}
		return count++;
	}

	/**
	 * 和配置文件的字段做匹配
	 * @param propsName 配置文件名(如sysConfig.properties)
	 * @param propsField 配置文件中需要匹配的key
	 * @param value 需要匹配的值
	 * @param sign 每个结果的分隔符
	 * @return
	 */
//	public static boolean propsContrast(String propsName,String propsField,String value,String sign){
//		boolean flag = false;
//		ReadPropsUtils readPropsUtils = new ReadPropsUtils(propsName);
//		String s = readPropsUtils.getValue(propsField);
//		String[] result = s.split(sign);
//		for (String r : result) {
//			if(r.equals(value)){
//				flag = true;
//				break;
//			}
//		}
//		return flag;
//	}
    /**
     * 判断数组是否存在某个元素
     * @param arr
     * @param targetValue
     * @return
     */
	public static boolean useList(String[] arr, String targetValue) {
		if(!TypeUtils.isEmpty(arr)){
			return Arrays.asList(arr).contains(targetValue);
		}
	    return false;
	}

	/**
	 * 获取分页页数
	 * @param o
	 */
	public static int getPage(Object o){
		String s=getString(o);
		int index=getInt(s)-1;
		index=index<=0?0:index;
		return index;
	}

	/**
	 * 获取本地HttpUrl
	 * @return
	 */
	public static String getHttpUrl(HttpServletRequest request){
		String PATH = request.getContextPath();
		String BASE_URL  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String BASE_PATH = BASE_URL + PATH +"/";
		return BASE_PATH;
	}

	/**
     * 提供double精确的加法运算。
     * @param v1   加数
     * @param v2   加数
     * @return v1+v2 的和
     */
    public static double addDouble(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(!isEmpty(v1)?v1:0));
        BigDecimal b2 = new BigDecimal(Double.toString(!isEmpty(v2)?v2:0));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供double精确的减法运算。
     * @param v1   被减数
     * @param v2   减数
     * @return v1-v2的差
     */
    public static double subDouble(double v1, double v2) {
    	BigDecimal b1 = new BigDecimal(Double.toString(!isEmpty(v1)?v1:0));
        BigDecimal b2 = new BigDecimal(Double.toString(!isEmpty(v2)?v2:0));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供double精确的乘法运算。
     * @param v1   被乘数
     * @param v2   乘数
     * @return v1*v2的积
     */
    public static double multiplyDouble(double v1, double v2) {
    	BigDecimal b1 = new BigDecimal(Double.toString(!isEmpty(v1)?v1:0));
        BigDecimal b2 = new BigDecimal(Double.toString(!isEmpty(v2)?v2:0));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供double精确的除法运算。
     * @param v1   被除数
     * @param v2   除数
     * @param scale 四舍五入保留多少位小数
     * @return v1-v2的商
     */
    public static double divideDouble(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 实体类对象转换成Map
     * @param obj
     * @return
     */
    public static Map<String, Object> convertObjToMap(Object obj) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        if (obj == null){
            return null;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field f = obj.getClass().getDeclaredField(
                        fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

//    public static void main(String[] args) {
//    	System.out.println(TypeUtils.useList(null, ""));
//	}
}
