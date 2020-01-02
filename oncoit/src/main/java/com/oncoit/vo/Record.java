package com.oncoit.vo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.SimpleTypeConverter;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Author: gyao
 * Date: 12/22/19
 * E-mail: yaoguocai_cool@163.com
 **/
public class Record extends HashMap<String,Object> {
    private static final long serialVersionUID=1L;

    private SimpleTypeConverter converter=new SimpleTypeConverter();

    /**
     * 字符大写匹配
     */
    private static final Pattern upperCasePattern = Pattern.compile("[A-Z]");
    public Long getLong(String key){
        return get(key,Long.class);
    }
    public Double getDouble(String key){
        return get(key,Double.class);
    }
    public String getString(String key){
        return get(key,String.class);
    }
    public <T> T get(String key,Class<T> clazz){
        Object value=this.get(key);
        return value==null ? null : converter.convertIfNecessary(value,clazz);
    }
    @Override
    public Object put(String key,Object value){
        String strKey=upperCasePattern.matcher(key).find()?key:underlineToCamel(key);
        return super.put(strKey,value);
    }

    private String underlineToCamel(String str) {
        str= StringUtils.lowerCase(str);
        String[] array=StringUtils.split(str,"_");
        StringBuffer sb = new StringBuffer();
        boolean first=true;
        for(String item:array){
            if(!first){
                char[] chars=item.toCharArray();
                if (chars[0] >= 'a' && chars[0] <= 'z'){
                    chars[0]=(char)(chars[0]-32);
                }
                item=new String(chars);
            }
            first=false;
            sb.append(item);
        }
        return sb.toString();
    }
}
