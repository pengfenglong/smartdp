package com.smartdp.dbexportdoc;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;
import com.smartdp.dbexportdoc.entity.DBColumn;
import com.smartdp.dbexportdoc.entity.DBTable;

/**
 * 
 * @author pengfenglong
 * 
 */
public class DB2WordUtil {

	public static void export() throws Exception {

		/** 创建Document对象（word文档 */
		Document doc = new Document(PageSize.A4);
		/** 新建字节数组输出流 */
		RtfWriter2.getInstance(doc, new FileOutputStream(new File("test.doc")));
		doc.open();
		
		RtfFont font = new RtfFont("仿宋_GB2312", 15, Font.BOLD, Color.blue);

		/** 标题字体 */
		RtfFont titleFont = new RtfFont("仿宋_GB2312", 10, Font.NORMAL,
				Color.BLACK);
		/** 正文字体 */
		RtfFont contextFont = new RtfFont("仿宋_GB2312", 9, Font.NORMAL,
				Color.BLACK);

		List<DBTable> dbTables = DbTableFactory.getInstance().getAllTables();

		for (DBTable dbTable : dbTables) {
			
			String titleString = dbTable.getRemarks()+"("+dbTable.getName()+")";  
			Phrase title = new Phrase(titleString);  
	        // 设置标题格式对其方式  
	        title.setFont(font);  
	        doc.add(title);

			/** 表格设置 */
			Table table = new Table(8, dbTable.getColumns().size());
			int[] withs = { 1, 2, 3, 2, 2, 2, 2, 2 };
			/** 设置每列所占比例 */
			table.setWidths(withs);
			/** 表格所占页面宽度 */
			table.setWidth(100);
			/** 居中显示 */
			table.setAlignment(Element.ALIGN_CENTER);
			/** 自动填满 */
			table.setAutoFillEmptyCells(true);

			/** 第三行（表格） */
			Cell[] cellHeaders = new Cell[8];
			cellHeaders[0] = new Cell(new Phrase("序号", titleFont));
			cellHeaders[1] = new Cell(new Phrase("字段名", titleFont));
			cellHeaders[2] = new Cell(new Phrase("注释", titleFont));
			cellHeaders[3] = new Cell(new Phrase("字段类型", titleFont));
			cellHeaders[4] = new Cell(new Phrase("字段长度", titleFont));
			cellHeaders[5] = new Cell(new Phrase("默认值", titleFont));
			cellHeaders[6] = new Cell(new Phrase("是否为空", titleFont));
			cellHeaders[7] = new Cell(new Phrase("是否是主键", titleFont));
			for (int i = 0; i < 8; i++) {
				/**背景颜色*/
				cellHeaders[i].setBackgroundColor(Color.LIGHT_GRAY);
				/** 居中显示 */
				cellHeaders[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				/** 纵向居中显示 */
				cellHeaders[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cellHeaders[i]);
			}
			/** 向表格填充数据 */
			int i = 1;
			for (DBColumn dbColumn : dbTable.getColumns()) {
				Cell cell0 = new Cell(new Phrase(String.valueOf(i++),
						contextFont));
				table.addCell(cell0);
				Cell cell1 = new Cell(new Phrase(dbColumn.getName(),
						contextFont));
				table.addCell(cell1);
				Cell cell2 = new Cell(new Phrase(dbColumn.getRemarks(),
						contextFont));
				table.addCell(cell2);
				Cell cell3 = new Cell(new Phrase(dbColumn.getType(),
						contextFont));
				table.addCell(cell3);
				Cell cell4 = new Cell(new Phrase(String.valueOf(dbColumn
						.getSize()), contextFont));
				table.addCell(cell4);
				Cell cell5 = new Cell(new Phrase(dbColumn.getDefaultValue(),
						contextFont));
				table.addCell(cell5);
				Cell cell6 = new Cell(new Phrase(String.valueOf(dbColumn
						.isNullable()), contextFont));
				table.addCell(cell6);
				Cell cell7 = new Cell(new Phrase(
						String.valueOf(dbColumn.isPk()), contextFont));
				table.addCell(cell7);

			}
			doc.add(table);
			doc.add(Chunk.NEWLINE);
		}
		doc.close();

	}

	public String str_changenbsp(String str) {
		if (str != null) {
			return str.replaceAll("&nbsp;", "");
		} else {
			return "";
		}
	}

	public String str_changebr(String str) {
		if (str != null) {
			return str.replaceAll("<br>", "\n");
		} else {
			return "";
		}
	}
}
