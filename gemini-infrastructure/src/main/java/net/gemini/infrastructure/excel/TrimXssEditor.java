package net.gemini.infrastructure.excel;

import cn.hutool.http.HtmlUtil;
import cn.hutool.poi.excel.cell.CellEditor;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author edison
 */
public class TrimXssEditor implements CellEditor {

    @Override
    public Object edit(Cell cell, Object value) {
        if (value instanceof String) {
            return HtmlUtil.cleanHtmlTag(value.toString());
        }
        return value;
    }
}
