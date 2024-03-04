package net.gemini.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;

/**
 * @author edison
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractPageQuery<T> extends AbstractQuery<T> {

    public static final int MAX_PAGE_NUM = 100;
    public static final int MAX_PAGE_SIZE = 500;

    @Max(value = MAX_PAGE_NUM, message = "容量超出最大值")
    protected Integer pageNum = 1;
    @Max(value = MAX_PAGE_SIZE, message = "页码超过最大值")
    protected Integer pageSize = 10;


    /**
     * 转换分页对象
     */
    public Page<T> toPage() {
        return new Page<>(pageNum, pageSize);
    }
}
