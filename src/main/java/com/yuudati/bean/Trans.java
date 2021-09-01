package com.yuudati.bean;

import lombok.Data;

/**
 * @author Created by lixindong[lixindong@kuke.com].
 * @date 2021/9/1
 */
@Data
public class Trans {

    /**
     * 交换的
     */
    private Integer source;

    /**
     * 目标
     */
    private Integer target;

}
