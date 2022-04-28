package com.cz.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * pageable 转 Page
 *
 * @param <T>
 * @author chaixuhong
 * @date 2021年12月29日
 */
public class PageUtil<T> extends Page<T> {


    public static List toPage(int page, int size , List list) {
        page--;
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }

    /**
     * pageable 转 page
     *
     * @param pageable
     */
    public PageUtil(Pageable pageable) {
        super(pageable.getPageNumber(), pageable.getPageSize());
        Iterator<Sort.Order> iterator = pageable.getSort().stream().iterator();
        while (iterator.hasNext()) {
            Sort.Order next = iterator.next();
            this.addOrder(new OrderItem(StringUtils.camelToUnderline(next.getProperty()), next.getDirection().isAscending()));
        }
    }
}
