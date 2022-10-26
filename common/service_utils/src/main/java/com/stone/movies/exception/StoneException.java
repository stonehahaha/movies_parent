package com.stone.movies.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stonestart
 * @create 2022/10/20 - 1:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoneException extends RuntimeException{
    private Integer code;
    private String msg;
}
