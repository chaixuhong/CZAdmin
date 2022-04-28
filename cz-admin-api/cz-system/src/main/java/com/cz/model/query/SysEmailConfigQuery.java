package com.cz.model.query;

import com.cz.annotation.Query;
import lombok.Data;

/**
* @author chaizi
* @date 2022-04-25
**/
@Data
public class SysEmailConfigQuery{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String fromUser;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String host;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String user;
}