package net.gemini.common.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author edison
 */
@Data
public abstract class BaseVO<T> implements Serializable {

    public static final int MAX_PAGE_NUM = 100;
    public static final int MAX_PAGE_SIZE = 500;

    @Max(value = MAX_PAGE_NUM, message = "容量超出最大值")
    protected Integer pageNum = 1;
    @Max(value = MAX_PAGE_SIZE, message = "页码超过最大值")
    protected Integer pageSize = 10;
    protected String keywords;

    // 排序
    protected String orderColumn;
    protected String orderDirection;
    private static final String ASC = "asc";
    private static final String DESC = "desc";

    // 时间区间
    protected String timeRangeColumn;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 转换分页对象
     */
    public Page<T> toPage() {
        return new Page<>(pageNum, pageSize);
    }

    /**
     * 构建业务查询条件
     */
    public abstract QueryWrapper<T> addQueryCondition();

    /**
     * 整合查询条件
     */
    public QueryWrapper<T> toQueryWrapper() {
        QueryWrapper<T> queryWrapper = addQueryCondition();
        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper);
        return queryWrapper;
    }

    /**
     * 构建排序条件
     */
    public void addSortCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper == null || StrUtil.isEmpty(orderColumn)) return;
        Boolean sortDirection = convertSortDirection();
        if (sortDirection != null) {
            queryWrapper.orderBy(StrUtil.isNotEmpty(orderColumn), sortDirection,
                    StrUtil.toUnderlineCase(orderColumn));
        }
    }

    /**
     * 构建时间条件
     */
    public void addTimeCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper != null && StrUtil.isNotEmpty(this.timeRangeColumn)) {
            queryWrapper
                    .ge(Objects.nonNull(beginTime),
                            StrUtil.toUnderlineCase(timeRangeColumn),
                            DateUtil.beginOfDay(beginTime))
                    .le(Objects.nonNull(endTime),
                            StrUtil.toUnderlineCase(timeRangeColumn),
                            DateUtil.endOfDay(endTime));
        }
    }

    /**
     * 转换排序方向
     */
    private Boolean convertSortDirection() {
        Boolean isAsc = null;
        if (StrUtil.isEmpty(this.orderDirection)) {
            return isAsc;
        }
        if (ASC.equals(this.orderDirection)) {
            isAsc = true;
        }
        if (DESC.equals(this.orderDirection)) {
            isAsc = false;
        }
        return isAsc;
    }

}
