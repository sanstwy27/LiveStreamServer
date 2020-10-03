package com.sanstwy27.server.info.util;

import com.sanstwy27.server.info.bean.StreamInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sanstwy27
 * @create 10/1/2020
 */

public class MyUtil {

    public static List<?> getSubList(List<?> srcList, int page, int offset) {
        if(srcList == null) return null;

        int _offset = Math.max(1, offset);
        int totalPages = (int) Math.ceil((double) srcList.size() / _offset);
        int _start = (page  >  totalPages ? Math.max(0, totalPages - 1) : Math.max(0, page - 1)) * _offset;
        int _end = Math.min(srcList.size(), _start + _offset);
        return srcList.subList(_start, _end);
    }

    public static Map<String, Object> packInfo(List<StreamInfo> srcList, int numberStreams) {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("data", srcList);
        ret.put("total", numberStreams);
        return ret;
    }

}
