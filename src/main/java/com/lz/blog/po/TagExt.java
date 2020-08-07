package com.lz.blog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lz
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagExt extends Tag {
    private Integer pageNow = 1;
    private Integer pageSize = 5;
}
