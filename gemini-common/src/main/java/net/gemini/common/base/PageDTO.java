package net.gemini.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 分页结果
 * @author edison
 */
@Data
public class PageDTO<T> {

    private Long total;
    private List<T> records;

    public PageDTO(List<T> records) {
        this.records = records;
        this.total = (long) records.size();
    }

    public PageDTO(Page<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
    }

    public PageDTO(List<T> records, Long total) {
        this.records = records;
        this.total = total;
    }
}
