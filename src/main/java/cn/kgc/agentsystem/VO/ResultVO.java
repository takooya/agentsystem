package cn.kgc.agentsystem.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private T data;
}
